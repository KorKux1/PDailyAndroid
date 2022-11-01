package co.edu.icesi.pdailyandroid.model.redux.reducer;

import java.util.ArrayList;
import java.util.List;

import co.edu.icesi.pdailyandroid.model.redux.actions.Action;
import co.edu.icesi.pdailyandroid.model.redux.actions.AddItemAction;
import co.edu.icesi.pdailyandroid.model.redux.state.TodoState;


//El reducer recibe el estado viejo y la accion. El reducer calcula el nuevo estado

public class TodoReducer implements Reducer<TodoState> {

    @Override
    public TodoState reduce(TodoState oldState, Action action) {
        if (action instanceof AddItemAction) {
            AddItemAction addItemAction = (AddItemAction) action;

            List<String> oldItems = oldState.getItems();

            List<String> newItems = new ArrayList<>(oldItems);
            newItems.add(addItemAction.getValue());

            return oldState.withItems(newItems);
        }
        return oldState;
    }
}


