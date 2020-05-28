package co.edu.icesi.pdailyandroid.model.redux.actions;

public class AddItemAction implements Action{

    private final String value;

    public AddItemAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
