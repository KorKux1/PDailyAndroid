package co.edu.icesi.pdailyandroid.model.redux.store;

import java.util.ArrayList;
import java.util.List;

import co.edu.icesi.pdailyandroid.model.redux.actions.Action;
import co.edu.icesi.pdailyandroid.model.redux.reducer.Reducer;

public class Store <S>{
    private S currentState;
    private Reducer<S> reducer;

    List<Subscriber<S>> subscribers = new ArrayList<>();

    public Store(S initialState, Reducer<S> rootReducer){
        this.currentState = initialState;
        this.reducer = rootReducer;
    }

    public S getState() {
        return this.currentState;
    }

    private void notifySubscribers() {
        for(int i=0 ; i<subscribers.size() ; i++){
            Subscriber subscriber = subscribers.get(i);
            subscriber.onChange(this.currentState);
        }
    }

    public void dispatch(Object action) {
        S oldState = this.currentState;
        S newState = reducer.reduce(this.currentState, (Action) action);
        if (oldState != newState && !oldState.equals(newState)) {
            this.currentState = newState;
            notifySubscribers();
        }
    }

    public Subscription subscribe(Subscriber<S> subscriber) {
        subscribers.add(subscriber);
        subscriber.onChange(this.currentState);
        return () -> subscribers.remove(subscriber);

    }

}
