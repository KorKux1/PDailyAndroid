package co.edu.icesi.pdailyandroid.model.dto;

import java.util.UUID;

public class RoutineScheduleDTO {
    private UUID id;
    private UUID patientId;
    private UUID routineTypeId;
    private String typeLabel;
    private SchedulePlanDTO plan;

    public RoutineScheduleDTO() {
    }

    public RoutineScheduleDTO(
            UUID id, UUID patientId, UUID routineTypeId, String typeLabel, SchedulePlanDTO metadata) {
        this.id = id;
        this.patientId = patientId;
        this.routineTypeId = routineTypeId;
        this.typeLabel = typeLabel;
        this.plan = metadata;
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

    public String getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    public SchedulePlanDTO getPlan() {
        return plan;
    }

    public void setPlan(SchedulePlanDTO plan) {
        this.plan = plan;
    }
}
