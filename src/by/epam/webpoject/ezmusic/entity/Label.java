package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Anton Shulha on 12.12.2016.
 */
public class Label {

    private Long labelId;
    private String labelName;

    public Label(){

    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Label)) return false;

        Label label = (Label) o;

        if (!labelId.equals(label.labelId)) return false;
        return labelName.equals(label.labelName);

    }

    @Override
    public int hashCode() {
        int result = labelId.hashCode();
        result = 31 * result + labelName.hashCode();
        return result;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
