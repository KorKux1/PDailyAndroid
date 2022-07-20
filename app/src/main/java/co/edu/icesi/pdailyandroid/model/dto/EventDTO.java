package co.edu.icesi.pdailyandroid.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import co.edu.icesi.pdailyandroid.model.viewmodel.Event;

public class EventDTO {

    private static Map<String, String> eventTable = new HashMap<String, String>() {{
        put("Congelamiento", "94525afd-e8a2-4a16-9627-9bd129640575");
        put("Lentificación", "211aeb2a-3c33-42b8-b779-fae2fa793fe4");
        put("Discinesias", "a3a6fffa-6b90-4072-a1fa-481eceef99a4");
        put("Temblor", "e58d8c80-b421-4ce2-8582-ab2f89330bb7");
        put("Tropezones", "b59fcf70-cefd-423a-9e6c-b8f9527acf6b");
        put("Caídas", "c8255b06-8acf-434f-8c3c-a290e2d4d52d");

    }};

    private String patientId;
    private int intensity;
    private String injuryTypeId;
    private String date;
    private String finalDate;
    private ArrayList<String> bodyPartTypes;

    public EventDTO() {
        bodyPartTypes = new ArrayList<String>();
    }

    //Creación para envio
    public EventDTO(String patientId, int intensity, String injuryTypeId,
                    long startDate, long endDate, ArrayList<String> bodyPartTypes) {
        this.patientId = patientId;
        this.intensity = intensity;
        this.injuryTypeId = injuryTypeId;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = dateFormat.format(new Date(startDate)).replace(' ', 'T') + "Z";
        this.finalDate = dateFormat.format(new Date(endDate)).replace(' ', 'T') + "Z";

        this.bodyPartTypes = bodyPartTypes;
    }

    public static EventDTO transformToDTO(Event event, String patientId) {
        EventDTO dto = new EventDTO(
                patientId,
                event.getIntensity(),
                eventTable.get(event.getName()),
                event.getFrom(),
                event.getTo(),
                event.getBodyParts()
        );
        return dto;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String getInjuryTypeId() {
        return injuryTypeId;
    }

    public void setInjuryTypeId(String injuryTypeId) {
        this.injuryTypeId = injuryTypeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public ArrayList<String> getBodyPartTypes() {
        return bodyPartTypes;
    }

    public void setBodyPartTypes(ArrayList<String> bodyPartTypes) {
        this.bodyPartTypes = bodyPartTypes;
    }

}
