package co.edu.icesi.pdailyandroid.dto;

public class BodyDTO {

    private String id;
    private String bodyPartId;
    private String bodyPartName;

    public BodyDTO() {
    }

    public BodyDTO(String bodyPartId) {
        this.id = id;
        this.bodyPartId = bodyPartId;
        this.bodyPartName = bodyPartName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBodyPartId() {
        return bodyPartId;
    }

    public void setBodyPartId(String bodyPartId) {
        this.bodyPartId = bodyPartId;
    }

    public String getBodyPartName() {
        return bodyPartName;
    }

    public void setBodyPartName(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }
}
