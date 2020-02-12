package co.edu.icesi.pdailyandroid.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import co.edu.icesi.pdailyandroid.model.Event;

public class EventDTO {

    private static Map<String,String> eventTable = new HashMap<String,String>() {{
        put("Congelamiento", "94525afd-e8a2-4a16-9627-9bd129640575");
        put("Lentificación", "211aeb2a-3c33-42b8-b779-fae2fa793fe4");
        put("Discinesias", "a3a6fffa-6b90-4072-a1fa-481eceef99a4");
        put("Temblor", "e58d8c80-b421-4ce2-8582-ab2f89330bb7");
        put("Tropezones", "b59fcf70-cefd-423a-9e6c-b8f9527acf6b");
        put("Caídas", "c8255b06-8acf-434f-8c3c-a290e2d4d52d");

    }};

    private String id;
    private String patientId;
    private int intensity;
    private String injuryTypeId;
    private String injuryTypeName;
    private long initialDate;
    private long finalDate;
    private ArrayList<BodyDTO> bodyDetails;

    public EventDTO() {
        bodyDetails = new ArrayList<>();
    }

    //Creación para envio
    public EventDTO(String patientId, int intensity, String injuryTypeId, long initialDate, long finalDate) {
        this.patientId = patientId;
        this.intensity = intensity;
        this.injuryTypeId = injuryTypeId;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.bodyDetails = new ArrayList<>();
    }

    public static EventDTO transformToDTO(Event event) {
        EventDTO dto = new EventDTO(
                UUID.randomUUID().toString(),
                event.getIntensity(),
                eventTable.get( event.getName() ),
                event.getFrom(),
                event.getTo()
        );
        dto.setStringBodyDetails(event.getBodyParts());
        return dto;
    }

    private void setStringBodyDetails(ArrayList<String> bodyParts) {
        ArrayList<BodyDTO> bodyDTOS = new ArrayList<>();
        for(int i=0 ; i<bodyParts.size() ; i++) {
            BodyDTO bodyDTO = new BodyDTO(bodyParts.get(i));
            bodyDTOS.add(bodyDTO);
        }
        this.bodyDetails = bodyDTOS;
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

    public long getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(long initialDate) {
        this.initialDate = initialDate;
    }

    public long getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(long finalDate) {
        this.finalDate = finalDate;
    }

    public ArrayList<BodyDTO> getBodyDetails() {
        return bodyDetails;
    }

    public void setBodyDetails(ArrayList<BodyDTO> bodyDetails) {
        this.bodyDetails = bodyDetails;
    }

}
