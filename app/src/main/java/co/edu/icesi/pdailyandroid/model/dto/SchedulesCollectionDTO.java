package co.edu.icesi.pdailyandroid.model.dto;

import java.util.ArrayList;

public class SchedulesCollectionDTO {
    private ArrayList<FoodScheduleDTO> foodSchedules;
    private ArrayList<MedicineScheduleDTO> medicineLevodopa;
    private ArrayList<RoutineScheduleDTO> routineSchedules;

    public SchedulesCollectionDTO() {
    }

    public SchedulesCollectionDTO(
            ArrayList<FoodScheduleDTO> foodSchedules,
            ArrayList<MedicineScheduleDTO> medicineSchedules,
            ArrayList<RoutineScheduleDTO> routineSchedules) {
        this.foodSchedules = foodSchedules;
        this.medicineLevodopa = medicineSchedules;
        this.routineSchedules = routineSchedules;
    }

    public ArrayList<FoodScheduleDTO> getFoodSchedules() {
        return foodSchedules;
    }

    public void setFoodSchedules(ArrayList<FoodScheduleDTO> foodSchedules) {
        this.foodSchedules = foodSchedules;
    }

    public ArrayList<MedicineScheduleDTO> getMedicineLevodopa() {
        return medicineLevodopa;
    }

    public void setMedicineLevodopa(ArrayList<MedicineScheduleDTO> medicineLevodopa) {
        this.medicineLevodopa = medicineLevodopa;
    }

    public ArrayList<RoutineScheduleDTO> getRoutineSchedules() {
        return routineSchedules;
    }

    public void setRoutineSchedules(ArrayList<RoutineScheduleDTO> routineSchedules) {
        this.routineSchedules = routineSchedules;
    }

    public boolean isValid() {
        return this.foodSchedules != null &&
                this.routineSchedules != null &&
                this.medicineLevodopa != null;
    }
}
