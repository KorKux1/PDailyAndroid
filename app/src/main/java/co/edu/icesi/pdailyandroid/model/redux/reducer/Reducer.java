package co.edu.icesi.pdailyandroid.model.redux.reducer;

import co.edu.icesi.pdailyandroid.model.redux.actions.Action;

public interface Reducer<S> {
    S reduce(S oldState, Action action);
}


