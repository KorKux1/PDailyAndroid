package co.edu.icesi.pdailyandroid.model.dto;

import java.util.UUID;

public class MedicineLevodopaScheduleDTO {
    private UUID id;
    private UUID patientId;
    private UUID medicineLevodopaTypeId;
    private String medicineLevodopaTypeLabel;
    private double medicineLevodopaTypeQuantity;
    private String medicineLevodopaUnitsTypeLabel;
    private double scheduledDose;
    private SchedulePlanDTO metadata;

    public MedicineLevodopaScheduleDTO() {
    }

    public MedicineLevodopaScheduleDTO(
            UUID id, UUID patientId, UUID medicineLevodopaTypeId, String medicineLevodopaTypeLabel,
            double medicineLevodopaTypeQuantity, String medicineLevodopaUnitsTypeLabel,
            double scheduledDose, SchedulePlanDTO metadata) {
        this.id = id;
        this.patientId = patientId;
        this.medicineLevodopaTypeId = medicineLevodopaTypeId;
        this.medicineLevodopaTypeLabel = medicineLevodopaTypeLabel;
        this.medicineLevodopaTypeQuantity = medicineLevodopaTypeQuantity;
        this.medicineLevodopaUnitsTypeLabel = medicineLevodopaUnitsTypeLabel;
        this.scheduledDose = scheduledDose;
        this.metadata = metadata;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public UUID getMedicineLevodopaTypeId() {
        return medicineLevodopaTypeId;
    }

    public void setMedicineLevodopaTypeId(UUID medicineLevodopaTypeId) {
        this.medicineLevodopaTypeId = medicineLevodopaTypeId;
    }

    public String getMedicineLevodopaTypeLabel() {
        return medicineLevodopaTypeLabel;
    }

    public void setMedicineLevodopaTypeLabel(String medicineLevodopaTypeLabel) {
        this.medicineLevodopaTypeLabel = medicineLevodopaTypeLabel;
    }

    public double getMedicineLevodopaTypeQuantity() {
        return medicineLevodopaTypeQuantity;
    }

    public void setMedicineLevodopaTypeQuantity(double medicineLevodopaTypeQuantity) {
        this.medicineLevodopaTypeQuantity = medicineLevodopaTypeQuantity;
    }

    public String getMedicineLevodopaUnitsTypeLabel() {
        return medicineLevodopaUnitsTypeLabel;
    }

    public void setMedicineLevodopaUnitsTypeLabel(String medicineLevodopaUnitsTypeLabel) {
        this.medicineLevodopaUnitsTypeLabel = medicineLevodopaUnitsTypeLabel;
    }

    public double getScheduledDose() {
        return scheduledDose;
    }

    public void setScheduledDose(double scheduledDose) {
        this.scheduledDose = scheduledDose;
    }

    public SchedulePlanDTO getMetadata() {
        return metadata;
    }

    public void setMetadata(SchedulePlanDTO metadata) {
        this.metadata = metadata;
    }
}
