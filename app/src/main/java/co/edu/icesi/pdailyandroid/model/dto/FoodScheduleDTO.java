package co.edu.icesi.pdailyandroid.model.dto;

import java.util.UUID;

public class FoodScheduleDTO {
    private UUID id;
    private UUID patientId;
    private SchedulePlanDTO metadata;

    public FoodScheduleDTO() {
    }

    public FoodScheduleDTO(UUID id, UUID patientId, SchedulePlanDTO metadata) {
        this.id = id;
        this.patientId = patientId;
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

    public SchedulePlanDTO getMetadata() {
        return metadata;
    }

    public void setMetadata(SchedulePlanDTO metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        // Food schedules are equals if have the same hours
        FoodScheduleDTO other = (FoodScheduleDTO) obj;
        boolean isEquals = this.patientId.equals(other.getPatientId()) &&
                this.metadata.getTimesString().equals(other.getMetadata().getTimesString());

        return isEquals;
    }
}
