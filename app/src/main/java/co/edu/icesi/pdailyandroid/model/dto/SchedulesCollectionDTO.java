package co.edu.icesi.pdailyandroid.model.dto;

import java.util.ArrayList;

public class SchedulesCollectionDTO {
    private ArrayList<FoodScheduleDTO> foodSchedules;
    private ArrayList<MedicineLevodopaScheduleDTO> medicineLevodopaSchedules;
    private ArrayList<RoutineScheduleDTO> routineSchedules;

    public SchedulesCollectionDTO() {
    }

    public SchedulesCollectionDTO(
            ArrayList<FoodScheduleDTO> foodSchedules,
            ArrayList<MedicineLevodopaScheduleDTO> medicineLevodopaSchedules,
            ArrayList<RoutineScheduleDTO> routineSchedules) {
        this.foodSchedules = foodSchedules;
        this.medicineLevodopaSchedules = medicineLevodopaSchedules;
        this.routineSchedules = routineSchedules;
    }

    public ArrayList<FoodScheduleDTO> getFoodSchedules() {
        return foodSchedules;
    }

    public void setFoodSchedules(ArrayList<FoodScheduleDTO> foodSchedules) {
        this.foodSchedules = foodSchedules;
    }

    public ArrayList<MedicineLevodopaScheduleDTO> getMedicineLevodopaSchedules() {
        return medicineLevodopaSchedules;
    }

    public void setMedicineLevodopaSchedules(ArrayList<MedicineLevodopaScheduleDTO> medicineLevodopaSchedules) {
        this.medicineLevodopaSchedules = medicineLevodopaSchedules;
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
                this.medicineLevodopaSchedules != null;
    }
}
