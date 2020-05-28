package co.edu.icesi.pdailyandroid.model.dto;

public class FcmDTO {

    private String to;
    private FoodDTO data;

    public FcmDTO() {
    }

    public FcmDTO(String to, FoodDTO data) {
        this.to = to;
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public FoodDTO getData() {
        return data;
    }

    public void setData(FoodDTO data) {
        this.data = data;
    }
}
