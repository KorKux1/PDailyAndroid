package co.edu.icesi.pdailyandroid.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnimicEventDTO {

    private String patientId;
    private String moodTypeId;
    private String date;

    public AnimicEventDTO() {
    }

    public AnimicEventDTO(String patientId, String typeId, Date date) {
        this.patientId = patientId;
        this.moodTypeId = typeId;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = dateFormat.format(date).replace(' ', 'T') + "Z";
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getMoodTypeId() {
        return moodTypeId;
    }

    public void setMoodTypeId(String moodTypeId) {
        this.moodTypeId = moodTypeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
