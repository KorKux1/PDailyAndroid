package co.edu.icesi.pdailyandroid.model.dto;

public class FoodEventDTO {

    private String id;
    private String patientId;
    private long date;

    public FoodEventDTO() {
    }

    public FoodEventDTO(String id, String patientId, long date) {
        this.patientId = patientId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
