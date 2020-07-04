package co.edu.icesi.pdailyandroid.model.dto;

public class AnimicEventDTO {

    private String patientId;
    private String typeId;
    private long ocurrenceDate;

    public AnimicEventDTO() {
    }

    public AnimicEventDTO(String patientId, String typeId, long ocurrenceDate) {
        this.patientId = patientId;
        this.typeId = typeId;
        this.ocurrenceDate = ocurrenceDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public long getOcurrenceDate() {
        return ocurrenceDate;
    }

    public void setOcurrenceDate(long ocurrenceDate) {
        this.ocurrenceDate = ocurrenceDate;
    }
}
