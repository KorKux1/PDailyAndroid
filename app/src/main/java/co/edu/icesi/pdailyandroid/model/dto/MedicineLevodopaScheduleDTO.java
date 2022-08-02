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
}
