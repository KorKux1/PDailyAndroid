package co.edu.icesi.pdailyandroid.model.dto;

import java.text.DecimalFormat;
import java.util.UUID;

public class MedicineScheduleDTO {
    private UUID id;
    private UUID patientId;
    private UUID medicineTypeId;
    private String typeLabel;
    private double typeQuantity;
    private String typeUnits;
    private String typeFamily;
    private double scheduledDose;
    private SchedulePlanDTO plan;

    public MedicineScheduleDTO() {
    }

    public MedicineScheduleDTO(
            UUID id, UUID patientId, UUID medicineTypeId, String typeLabel,
            double typeQuantity, String typeUnits, String typeFamily,
            double scheduledDose, SchedulePlanDTO plan) {
        this.id = id;
        this.patientId = patientId;
        this.medicineTypeId = medicineTypeId;
        this.typeLabel = typeLabel;
        this.typeQuantity = typeQuantity;
        this.typeUnits = typeUnits;
        this.typeFamily = typeFamily;
        this.scheduledDose = scheduledDose;
        this.plan = plan;
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

    public UUID getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(UUID medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }

    public String getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    public String getTypeFamily() {
        return typeFamily;
    }

    public void setTypeFamily(String typeFamily) {
        this.typeFamily = typeFamily;
    }

    public double getTypeQuantity() {
        return typeQuantity;
    }

    public void setTypeQuantity(double typeQuantity) {
        this.typeQuantity = typeQuantity;
    }

    public String getTypeUnits() {
        return typeUnits;
    }

    public void setTypeUnits(String typeUnits) {
        this.typeUnits = typeUnits;
    }

    public double getScheduledDose() {
        return scheduledDose;
    }

    public void setScheduledDose(double scheduledDose) {
        this.scheduledDose = scheduledDose;
    }

    public SchedulePlanDTO getPlan() {
        return plan;
    }

    public void setPlan(SchedulePlanDTO plan) {
        this.plan = plan;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        // Schedules are equal if have the same id
        MedicineScheduleDTO other = (MedicineScheduleDTO) obj;
        boolean isEquals = this.patientId.equals(other.getPatientId()) && this.id.equals(other.id);

        return isEquals;
    }

    public String getTypeString() {
        DecimalFormat df = new DecimalFormat("0.#####");
        return String.format("%s x%s%s", getTypeLabel(), df.format(getTypeQuantity()), getTypeUnits());
    }
}
