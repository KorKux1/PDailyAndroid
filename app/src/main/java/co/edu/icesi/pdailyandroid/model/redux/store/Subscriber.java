package co.edu.icesi.pdailyandroid.model.redux.store;

public interface Subscriber<S> {
    void onChange(S state);
}
