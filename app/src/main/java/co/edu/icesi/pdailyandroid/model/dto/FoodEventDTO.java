package co.edu.icesi.pdailyandroid.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodEventDTO {

    private String patientId;
    private String date;

    public FoodEventDTO() {
    }

    public FoodEventDTO(String patientId, Date date) {
        this.patientId = patientId;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = dateFormat.format(date).replace(' ', 'T') + "Z";
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
