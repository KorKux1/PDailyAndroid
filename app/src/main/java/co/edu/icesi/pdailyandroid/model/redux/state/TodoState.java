package co.edu.icesi.pdailyandroid.model.redux.state;

import java.util.List;


//Aqui se modela el estado de la aplicacion. En este caso es una parte del estado del todo list
public class TodoState {

    private final List<String> items;

    public TodoState(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    public TodoState withItems(List<String> items) {
        if (items.equals(this.items)) {
            return this;
        } else {
            return new TodoState(items);
        }
    }
}


