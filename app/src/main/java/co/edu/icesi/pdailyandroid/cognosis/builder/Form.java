package co.edu.icesi.pdailyandroid.cognosis.builder;

public class Form {
    int form_id;
    String form_name;
    String form_description;
    String form_language;
    Question form_questions[];

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public String getForm_name() {
        return form_name;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }

    public String getForm_description() {
        return form_description;
    }

    public void setForm_description(String form_description) {
        this.form_description = form_description;
    }

    public String getForm_language() {
        return form_language;
    }

    public void setForm_language(String form_language) {
        this.form_language = form_language;
    }

    public Question[] getForm_questions() {
        return form_questions;
    }

    public void setForm_questions(Question[] form_questions) {
        this.form_questions = form_questions;
    }
}
