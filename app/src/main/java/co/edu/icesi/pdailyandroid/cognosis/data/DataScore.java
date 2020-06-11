package co.edu.icesi.pdailyandroid.cognosis.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataScore {
    private static DataScore reference = null;

    private int[] form_answers_pd_nms;
    private int form_score_pd_nms;

    private int[] form_answers_pd_cfrs;
    private int form_score_pd_cfrs;

    private int[] form_answers_walk;
    private int form_score_walk;

    private int[] form_answers_phq9;
    private int form_score_phq9;

    private ArrayList<String> moca_answers_tmt;
    private String moca_score_tmt;

    private ArrayList<String> moca_selection_words;
    private ArrayList<String> moca_answers_words;
    private int moca_score_words;

    private DataScore() {
    }

    public static DataScore getInstance() {
        if (reference == null) {
            reference = new DataScore();
        }
        return reference;
    }

    public int[] getForm_answers_pd_nms() {
        return form_answers_pd_nms;
    }

    public void setForm_answers_pd_nms(int[] form_answers_pd_nms) {
        this.form_answers_pd_nms = form_answers_pd_nms;
    }

    public int getForm_score_pd_nms() {
        return form_score_pd_nms;
    }

    public void setForm_score_pd_nms(int form_score_pd_nms) {
        this.form_score_pd_nms = form_score_pd_nms;
    }

    public int[] getForm_answers_pd_cfrs() {
        return form_answers_pd_cfrs;
    }

    public void setForm_answers_pd_cfrs(int[] form_answers_pd_cfrs) {
        this.form_answers_pd_cfrs = form_answers_pd_cfrs;
    }

    public int getForm_score_pd_cfrs() {
        return form_score_pd_cfrs;
    }

    public void setForm_score_pd_cfrs(int form_score_pd_cfrs) {
        this.form_score_pd_cfrs = form_score_pd_cfrs;
    }

    public int[] getForm_answers_walk() {
        return form_answers_walk;
    }

    public void setForm_answers_walk(int[] form_answers_walk) {
        this.form_answers_walk = form_answers_walk;
    }

    public int getForm_score_walk() {
        return form_score_walk;
    }

    public void setForm_score_walk(int form_score_walk) {
        this.form_score_walk = form_score_walk;
    }

    public int[] getForm_answers_phq9() {
        return form_answers_phq9;
    }

    public void setForm_answers_phq9(int[] form_answers_phq9) {
        this.form_answers_phq9 = form_answers_phq9;
    }

    public int getForm_score_phq9() {
        return form_score_phq9;
    }

    public void setForm_score_phq9(int form_score_phq9) {
        this.form_score_phq9 = form_score_phq9;
    }

    public ArrayList<String> getMoca_answers_tmt() {
        return moca_answers_tmt;
    }

    public void setMoca_answers_tmt(ArrayList<String> moca_answers_tmt) {
        this.moca_answers_tmt = moca_answers_tmt;
    }

    public String getMoca_score_tmt() {
        return moca_score_tmt;
    }

    public void setMoca_score_tmt(String moca_score_tmt) {
        this.moca_score_tmt = moca_score_tmt;
    }

    public ArrayList<String> getMoca_selection_words() {
        return moca_selection_words;
    }

    public void setMoca_selection_words(ArrayList<String> moca_selection_words) {
        this.moca_selection_words = moca_selection_words;
    }

    public ArrayList<String> getMoca_answers_words() {
        return moca_answers_words;
    }

    public void setMoca_answers_words(ArrayList<String> moca_answers_words) {
        this.moca_answers_words = moca_answers_words;
    }

    public int getMoca_score_words() {
        return moca_score_words;
    }

    public void setMoca_score_words(int moca_score_words) {
        this.moca_score_words = moca_score_words;
    }
}
