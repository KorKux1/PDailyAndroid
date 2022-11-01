package co.edu.icesi.pdailyandroid.model.dto;

public class FoodDTO {

    private String id;
    private String type;
    private String schedule;

    public FoodDTO(String id, String type, String schedule) {
        this.id = id;
        this.type = type;
        this.schedule = schedule;
    }

    public FoodDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
