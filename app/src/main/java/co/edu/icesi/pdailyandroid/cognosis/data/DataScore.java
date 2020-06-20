package co.edu.icesi.pdailyandroid.cognosis.data;

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
    private ArrayList<ArrayList<String>> moca_selection_words_noise;
    private ArrayList<String> moca_answers_words;
    private int moca_selector_words;
    private int moca_score_words;

    private ArrayList<Boolean> moca_total_Correct_List;
    private int moca_total_Correct;

    private ArrayList<String> moca_selection_letters;
    private ArrayList<String> moca_answers_letters;
    private ArrayList<Integer> moca_tapped_letters;
    private ArrayList<Double> moca_time_response_letters;
    private int moca_mistakes_letters;
    private Boolean moca_isTestApproved_letters;
    private int moca_score_letters;

    private ArrayList<ArrayList<String>> go_selection_letters;
    private ArrayList<ArrayList<String>> go_answers_letters;
    private ArrayList<Boolean> go_selection_stimulus;
    private ArrayList<Boolean> go_answer_stimulus;
    private ArrayList<Double> go_time_response;

    private DataScore() {
    }

    public static DataScore getInstance() {
        if (reference == null) {
            reference = new DataScore();
        }
        return reference;
    }


    public ArrayList<Boolean> getMoca_total_Correct_List() {
        return moca_total_Correct_List;
    }

    public void setMoca_total_Correct_List(ArrayList<Boolean> moca_total_Correct_List) {
        this.moca_total_Correct_List = moca_total_Correct_List;
    }

    public int getMoca_total_Correct() {
        return moca_total_Correct;
    }

    public void setMoca_total_Correct(int moca_total_Correct) {
        this.moca_total_Correct = moca_total_Correct;
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

    public int getMoca_selector_words() {
        return moca_selector_words;
    }

    public void setMoca_selector_words(int moca_selector_words) {
        this.moca_selector_words = moca_selector_words;
    }

    public int getMoca_score_words() {
        return moca_score_words;
    }

    public void setMoca_score_words(int moca_score_words) {
        this.moca_score_words = moca_score_words;
    }

    public ArrayList<ArrayList<String>> getMoca_selection_words_noise() {
        return moca_selection_words_noise;
    }

    public void setMoca_selection_words_noise(ArrayList<ArrayList<String>> moca_selection_words_noise) {
        this.moca_selection_words_noise = moca_selection_words_noise;
    }

    public ArrayList<String> getMoca_selection_letters() {
        return moca_selection_letters;
    }

    public void setMoca_selection_letters(ArrayList<String> moca_selection_letters) {
        this.moca_selection_letters = moca_selection_letters;
    }

    public ArrayList<String> getMoca_answers_letters() {
        return moca_answers_letters;
    }

    public void setMoca_answers_letters(ArrayList<String> moca_answers_letters) {
        this.moca_answers_letters = moca_answers_letters;
    }

    public ArrayList<Integer> getMoca_tapped_letters() {
        return moca_tapped_letters;
    }

    public void setMoca_tapped_letters(ArrayList<Integer> moca_tapped_letters) {
        this.moca_tapped_letters = moca_tapped_letters;
    }

    public ArrayList<Double> getMoca_time_response_letters() {
        return moca_time_response_letters;
    }

    public void setMoca_time_response_letters(ArrayList<Double> moca_time_response_letters) {
        this.moca_time_response_letters = moca_time_response_letters;
    }

    public int getMoca_mistakes_letters() {
        return moca_mistakes_letters;
    }

    public void setMoca_mistakes_letters(int moca_mistakes_letters) {
        this.moca_mistakes_letters = moca_mistakes_letters;
    }

    public Boolean getMoca_isTestApproved_letters() {
        return moca_isTestApproved_letters;
    }

    public void setMoca_isTestApproved_letters(Boolean moca_isTestApproved_letters) {
        this.moca_isTestApproved_letters = moca_isTestApproved_letters;
    }

    public int getMoca_score_letters() {
        return moca_score_letters;
    }

    public void setMoca_score_letters(int moca_score_letters) {
        this.moca_score_letters = moca_score_letters;
    }

    public ArrayList<ArrayList<String>> getGo_selection_letters() {
        return go_selection_letters;
    }

    public void setGo_selection_letters(ArrayList<ArrayList<String>> go_selection_letters) {
        this.go_selection_letters = go_selection_letters;
    }

    public ArrayList<ArrayList<String>> getGo_answers_letters() {
        return go_answers_letters;
    }

    public void setGo_answers_letters(ArrayList<ArrayList<String>> go_answers_letters) {
        this.go_answers_letters = go_answers_letters;
    }

    public ArrayList<Boolean> getGo_selection_stimulus() {
        return go_selection_stimulus;
    }

    public void setGo_selection_stimulus(ArrayList<Boolean> go_selection_stimulus) {
        this.go_selection_stimulus = go_selection_stimulus;
    }

    public ArrayList<Boolean> getGo_answer_stimulus() {
        return go_answer_stimulus;
    }

    public void setGo_answer_stimulus(ArrayList<Boolean> go_answer_stimulus) {
        this.go_answer_stimulus = go_answer_stimulus;
    }

    public ArrayList<Double> getGo_time_response() {
        return go_time_response;
    }

    public void setGo_time_response(ArrayList<Double> go_time_response) {
        this.go_time_response = go_time_response;
    }
}
