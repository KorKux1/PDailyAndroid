package co.edu.icesi.pdailyandroid.cognosis.builder;

public class Question {
    String question_type;
    String question_options[];
    String question_text;
    String question_ideal;

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String[] getQuestion_options() {
        return question_options;
    }

    public void setQuestion_options(String[] question_options) {
        this.question_options = question_options;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getQuestion_ideal() {
        return question_ideal;
    }

    public void setQuestion_ideal(String question_ideal) {
        this.question_ideal = question_ideal;
    }
}
