package co.edu.icesi.pdailyandroid.model.dto;

import java.util.UUID;

public class RoutineScheduleDTO {
    private UUID id;
    private UUID patientId;
    private UUID routineTypeId;
    private String routineTypeLabel;
    private SchedulePlanDTO metadata;

    public RoutineScheduleDTO() {
    }

    public RoutineScheduleDTO(
            UUID id, UUID patientId, UUID routineTypeId, String routineTypeLabel,
            SchedulePlanDTO metadata) {
        this.id = id;
        this.patientId = patientId;
        this.routineTypeId = routineTypeId;
        this.routineTypeLabel = routineTypeLabel;
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

    public UUID getRoutineTypeId() {
        return routineTypeId;
    }

    public void setRoutineTypeId(UUID routineTypeId) {
        this.routineTypeId = routineTypeId;
    }

    public String getRoutineTypeLabel() {
        return routineTypeLabel;
    }

    public void setRoutineTypeLabel(String routineTypeLabel) {
        this.routineTypeLabel = routineTypeLabel;
    }

    public SchedulePlanDTO getMetadata() {
        return metadata;
    }

    public void setMetadata(SchedulePlanDTO metadata) {
        this.metadata = metadata;
    }
}
