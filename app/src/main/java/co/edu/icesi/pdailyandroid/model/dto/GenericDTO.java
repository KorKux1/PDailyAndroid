package co.edu.icesi.pdailyandroid.model.dto;

public class GenericDTO {
    private String type;

    public GenericDTO(String type) {
        this.type = type;
    }

    public GenericDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
