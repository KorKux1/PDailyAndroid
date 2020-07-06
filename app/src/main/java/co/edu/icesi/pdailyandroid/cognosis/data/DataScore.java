package co.edu.icesi.pdailyandroid.cognosis.data;

import java.util.ArrayList;
import java.util.Date;

public class DataScore {
    private static DataScore reference = null;

    //PD-NMS
    private int form_score_pdnms;
    private int[] form_answers_pdnms;
    private long[] form_time_response_pdnms;
    private long form_time_response_pdnms_total;
    private Date form_date_response_pdnms;
    private Boolean form_isFinished_pdnms;

    //PD-CFRS
    private int form_score_pdcfrs;
    private int[] form_answers_pdcfrs;
    private long[] form_time_response_pcfrs;
    private long form_time_response_pdcfrs_total;
    private Date form_date_response_pdcfrs;
    private Boolean form_isFinished_pdcfrs;

    //WALK
    private int form_score_walk;
    private int[] form_answers_walk;
    private long[] form_time_response_walk;
    private long form_time_response_walk_total;
    private Date form_date_response_walk;
    private Boolean form_isFinished_walk;

    //PHQ-9
    private int form_score_phq9;
    private int[] form_answers_phq9;
    private long[] form_time_response_phq9;
    private long form_time_response_phq9_total;
    private Date form_date_response_phq9;
    private Boolean form_isFinished_phq9;

    //MoCA
    private Date moca_date_response;
    private Boolean moca_isFinished;

    private String moca_score_tmt;
    private ArrayList<String> moca_answers_tmt;
    private ArrayList<Long> moca_time_response_tmt;
    private long moca_time_response_tmt_total;
    private Boolean moca_isFinished_tmt;

    private ArrayList<String> moca_selected_words;
    private ArrayList<ArrayList<String>> moca_selected_words_noise;
    private ArrayList<String> moca_answers_words;
    private ArrayList<String> moca_mistakes_words;
    private int moca_score_words;
    private int moca_selector_words;
    private ArrayList<Long> moca_time_response_words;
    private long moca_time_response_words_total;
    private Boolean moca_isFinished_words;

    private int moca_score_substract;
    private ArrayList<Boolean> moca_answers_substract;
    private ArrayList<Long> moca_time_response_substract;
    private long moca_time_response_substract_total;
    private Boolean moca_isFinished_substract;

    private int moca_score_letters;
    private ArrayList<String> moca_answers_letters;
    private ArrayList<Integer> moca_tapped_letters;
    private ArrayList<String> moca_selected_letters;
    private ArrayList<Long> moca_time_response_letters;
    private long moca_time_response_letters_total;
    private ArrayList<Boolean> moca_mistakes_letters;
    private int moca_mistakes_letters_total;
    private Boolean moca_isFinished_letters;

    //GO
    private Date go_date_response;
    private Boolean go_isFinished;
    private String go_stimulus;

    private ArrayList<ArrayList<String>> go_selected_letters;
    private ArrayList<ArrayList<String>> go_answers_letters;
    private ArrayList<Boolean> go_selected_stimulus;
    private ArrayList<Boolean> go_answers_stimulus;
    private ArrayList<Long> go_time_response;
    private long go_time_response_total;
    private int go_score;

    //CATest
    private Date catest_date_response;
    private Boolean catest_isFinished;

    private ArrayList<String> catest_selected_words;
    private ArrayList<ArrayList<String>> catest_selected_words_noise;
    private ArrayList<String> catest_answers_words;
    private int catest_score_words;
    private int catest_selector_words;
    private ArrayList<Long> catest_time_response_words;
    private long catest_time_response_words_total;
    private Boolean catest_isFinished_words;

    private String catest_selected_speech_stimulus;
    private ArrayList<String> catest_answers_speech_words;
    private Date catest_date_response_speech;
    private Boolean catest_isFinished_speech;
    private String catest_score_speech;
    private String TestSelected;

    private DataScore() {
    }

    public static DataScore getInstance() {
        if (reference == null) {
            reference = new DataScore();
        }
        return reference;
    }

    public String getTestSelected() {
        return TestSelected;
    }

    public void setTestSelected(String testSelected) {
        TestSelected = testSelected;
    }

    public int getForm_score_pdnms() {
        return form_score_pdnms;
    }

    public void setForm_score_pdnms(int form_score_pdnms) {
        this.form_score_pdnms = form_score_pdnms;
    }

    public int[] getForm_answers_pdnms() {
        return form_answers_pdnms;
    }

    public void setForm_answers_pdnms(int[] form_answers_pdnms) {
        this.form_answers_pdnms = form_answers_pdnms;
    }

    public long[] getForm_time_response_pdnms() {
        return form_time_response_pdnms;
    }

    public void setForm_time_response_pdnms(long[] form_time_response_pdnms) {
        this.form_time_response_pdnms = form_time_response_pdnms;
    }

    public long getForm_time_response_pdnms_total() {
        return form_time_response_pdnms_total;
    }

    public void setForm_time_response_pdnms_total(long form_time_response_pdnms_total) {
        this.form_time_response_pdnms_total = form_time_response_pdnms_total;
    }

    public Date getForm_date_response_pdnms() {
        return form_date_response_pdnms;
    }

    public void setForm_date_response_pdnms(Date form_date_response_pdnms) {
        this.form_date_response_pdnms = form_date_response_pdnms;
    }

    public Boolean getForm_isFinished_pdnms() {
        return form_isFinished_pdnms;
    }

    public void setForm_isFinished_pdnms(Boolean form_isFinished_pdnms) {
        this.form_isFinished_pdnms = form_isFinished_pdnms;
    }

    public int getForm_score_pdcfrs() {
        return form_score_pdcfrs;
    }

    public void setForm_score_pdcfrs(int form_score_pdcfrs) {
        this.form_score_pdcfrs = form_score_pdcfrs;
    }

    public int[] getForm_answers_pdcfrs() {
        return form_answers_pdcfrs;
    }

    public void setForm_answers_pdcfrs(int[] form_answers_pdcfrs) {
        this.form_answers_pdcfrs = form_answers_pdcfrs;
    }

    public long[] getForm_time_response_pcfrs() {
        return form_time_response_pcfrs;
    }

    public void setForm_time_response_pcfrs(long[] form_time_response_pcfrs) {
        this.form_time_response_pcfrs = form_time_response_pcfrs;
    }

    public long getForm_time_response_pdcfrs_total() {
        return form_time_response_pdcfrs_total;
    }

    public void setForm_time_response_pdcfrs_total(long form_time_response_pdcfrs_total) {
        this.form_time_response_pdcfrs_total = form_time_response_pdcfrs_total;
    }

    public Date getForm_date_response_pdcfrs() {
        return form_date_response_pdcfrs;
    }

    public void setForm_date_response_pdcfrs(Date form_date_response_pdcfrs) {
        this.form_date_response_pdcfrs = form_date_response_pdcfrs;
    }

    public Boolean getForm_isFinished_pdcfrs() {
        return form_isFinished_pdcfrs;
    }

    public void setForm_isFinished_pdcfrs(Boolean form_isFinished_pdcfrs) {
        this.form_isFinished_pdcfrs = form_isFinished_pdcfrs;
    }

    public int getForm_score_walk() {
        return form_score_walk;
    }

    public void setForm_score_walk(int form_score_walk) {
        this.form_score_walk = form_score_walk;
    }

    public int[] getForm_answers_walk() {
        return form_answers_walk;
    }

    public void setForm_answers_walk(int[] form_answers_walk) {
        this.form_answers_walk = form_answers_walk;
    }

    public long[] getForm_time_response_walk() {
        return form_time_response_walk;
    }

    public void setForm_time_response_walk(long[] form_time_response_walk) {
        this.form_time_response_walk = form_time_response_walk;
    }

    public long getForm_time_response_walk_total() {
        return form_time_response_walk_total;
    }

    public void setForm_time_response_walk_total(long form_time_response_walk_total) {
        this.form_time_response_walk_total = form_time_response_walk_total;
    }

    public Date getForm_date_response_walk() {
        return form_date_response_walk;
    }

    public void setForm_date_response_walk(Date form_date_response_walk) {
        this.form_date_response_walk = form_date_response_walk;
    }

    public Boolean getForm_isFinished_walk() {
        return form_isFinished_walk;
    }

    public void setForm_isFinished_walk(Boolean form_isFinished_walk) {
        this.form_isFinished_walk = form_isFinished_walk;
    }

    public int getForm_score_phq9() {
        return form_score_phq9;
    }

    public void setForm_score_phq9(int form_score_phq9) {
        this.form_score_phq9 = form_score_phq9;
    }

    public int[] getForm_answers_phq9() {
        return form_answers_phq9;
    }

    public void setForm_answers_phq9(int[] form_answers_phq9) {
        this.form_answers_phq9 = form_answers_phq9;
    }

    public long[] getForm_time_response_phq9() {
        return form_time_response_phq9;
    }

    public void setForm_time_response_phq9(long[] form_time_response_phq9) {
        this.form_time_response_phq9 = form_time_response_phq9;
    }

    public long getForm_time_response_phq9_total() {
        return form_time_response_phq9_total;
    }

    public void setForm_time_response_phq9_total(long form_time_response_phq9_total) {
        this.form_time_response_phq9_total = form_time_response_phq9_total;
    }

    public Date getForm_date_response_phq9() {
        return form_date_response_phq9;
    }

    public void setForm_date_response_phq9(Date form_date_response_phq9) {
        this.form_date_response_phq9 = form_date_response_phq9;
    }

    public Boolean getForm_isFinished_phq9() {
        return form_isFinished_phq9;
    }

    public void setForm_isFinished_phq9(Boolean form_isFinished_phq9) {
        this.form_isFinished_phq9 = form_isFinished_phq9;
    }

    public Date getMoca_date_response() {
        return moca_date_response;
    }

    public void setMoca_date_response(Date moca_date_response) {
        this.moca_date_response = moca_date_response;
    }

    public Boolean getMoca_isFinished() {
        return moca_isFinished;
    }

    public void setMoca_isFinished(Boolean moca_isFinished) {
        this.moca_isFinished = moca_isFinished;
    }

    public String getMoca_score_tmt() {
        return moca_score_tmt;
    }

    public void setMoca_score_tmt(String moca_score_tmt) {
        this.moca_score_tmt = moca_score_tmt;
    }

    public ArrayList<String> getMoca_answers_tmt() {
        return moca_answers_tmt;
    }

    public void setMoca_answers_tmt(ArrayList<String> moca_answers_tmt) {
        this.moca_answers_tmt = moca_answers_tmt;
    }

    public ArrayList<Long> getMoca_time_response_tmt() {
        return moca_time_response_tmt;
    }

    public void setMoca_time_response_tmt(ArrayList<Long> moca_time_response_tmt) {
        this.moca_time_response_tmt = moca_time_response_tmt;
    }

    public long getMoca_time_response_tmt_total() {
        return moca_time_response_tmt_total;
    }

    public void setMoca_time_response_tmt_total(long moca_time_response_tmt_total) {
        this.moca_time_response_tmt_total = moca_time_response_tmt_total;
    }

    public Boolean getMoca_isFinished_tmt() {
        return moca_isFinished_tmt;
    }

    public void setMoca_isFinished_tmt(Boolean moca_isFinished_tmt) {
        this.moca_isFinished_tmt = moca_isFinished_tmt;
    }

    public ArrayList<String> getMoca_selected_words() {
        return moca_selected_words;
    }

    public void setMoca_selected_words(ArrayList<String> moca_selected_words) {
        this.moca_selected_words = moca_selected_words;
    }

    public ArrayList<ArrayList<String>> getMoca_selected_words_noise() {
        return moca_selected_words_noise;
    }

    public void setMoca_selected_words_noise(ArrayList<ArrayList<String>> moca_selected_words_noise) {
        this.moca_selected_words_noise = moca_selected_words_noise;
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

    public int getMoca_selector_words() {
        return moca_selector_words;
    }

    public void setMoca_selector_words(int moca_selector_words) {
        this.moca_selector_words = moca_selector_words;
    }

    public ArrayList<Long> getMoca_time_response_words() {
        return moca_time_response_words;
    }

    public void setMoca_time_response_words(ArrayList<Long> moca_time_response_words) {
        this.moca_time_response_words = moca_time_response_words;
    }

    public long getMoca_time_response_words_total() {
        return moca_time_response_words_total;
    }

    public void setMoca_time_response_words_total(long moca_time_response_words_total) {
        this.moca_time_response_words_total = moca_time_response_words_total;
    }

    public Boolean getMoca_isFinished_words() {
        return moca_isFinished_words;
    }

    public void setMoca_isFinished_words(Boolean moca_isFinished_words) {
        this.moca_isFinished_words = moca_isFinished_words;
    }

    public int getMoca_score_substract() {
        return moca_score_substract;
    }

    public void setMoca_score_substract(int moca_score_substract) {
        this.moca_score_substract = moca_score_substract;
    }

    public ArrayList<Boolean> getMoca_answers_substract() {
        return moca_answers_substract;
    }

    public void setMoca_answers_substract(ArrayList<Boolean> moca_answers_substract) {
        this.moca_answers_substract = moca_answers_substract;
    }

    public ArrayList<Long> getMoca_time_response_substract() {
        return moca_time_response_substract;
    }

    public void setMoca_time_response_substract(ArrayList<Long> moca_time_response_substract) {
        this.moca_time_response_substract = moca_time_response_substract;
    }

    public long getMoca_time_response_substract_total() {
        return moca_time_response_substract_total;
    }

    public void setMoca_time_response_substract_total(long moca_time_response_substract_total) {
        this.moca_time_response_substract_total = moca_time_response_substract_total;
    }

    public Boolean getMoca_isFinished_substract() {
        return moca_isFinished_substract;
    }

    public void setMoca_isFinished_substract(Boolean moca_isFinished_substract) {
        this.moca_isFinished_substract = moca_isFinished_substract;
    }

    public int getMoca_score_letters() {
        return moca_score_letters;
    }

    public void setMoca_score_letters(int moca_score_letters) {
        this.moca_score_letters = moca_score_letters;
    }

    public ArrayList<String> getMoca_answers_letters() {
        return moca_answers_letters;
    }

    public void setMoca_answers_letters(ArrayList<String> moca_answers_letters) {
        this.moca_answers_letters = moca_answers_letters;
    }

    public ArrayList<String> getMoca_mistakes_words() {
        return moca_mistakes_words;
    }

    public void setMoca_mistakes_words(ArrayList<String> moca_mistakes_words) {
        this.moca_mistakes_words = moca_mistakes_words;
    }

    public ArrayList<Integer> getMoca_tapped_letters() {
        return moca_tapped_letters;
    }

    public void setMoca_tapped_letters(ArrayList<Integer> moca_tapped_letters) {
        this.moca_tapped_letters = moca_tapped_letters;
    }

    public ArrayList<String> getMoca_selected_letters() {
        return moca_selected_letters;
    }

    public void setMoca_selected_letters(ArrayList<String> moca_selected_letters) {
        this.moca_selected_letters = moca_selected_letters;
    }

    public ArrayList<Long> getMoca_time_response_letters() {
        return moca_time_response_letters;
    }

    public void setMoca_time_response_letters(ArrayList<Long> moca_time_response_letters) {
        this.moca_time_response_letters = moca_time_response_letters;
    }

    public long getMoca_time_response_letters_total() {
        return moca_time_response_letters_total;
    }

    public void setMoca_time_response_letters_total(long moca_time_response_letters_total) {
        this.moca_time_response_letters_total = moca_time_response_letters_total;
    }

    public ArrayList<Boolean> getMoca_mistakes_letters() {
        return moca_mistakes_letters;
    }

    public void setMoca_mistakes_letters(ArrayList<Boolean> moca_mistakes_letters) {
        this.moca_mistakes_letters = moca_mistakes_letters;
    }

    public int getMoca_mistakes_letters_total() {
        return moca_mistakes_letters_total;
    }

    public void setMoca_mistakes_letters_total(int moca_mistakes_letters_total) {
        this.moca_mistakes_letters_total = moca_mistakes_letters_total;
    }

    public Boolean getMoca_isFinished_letters() {
        return moca_isFinished_letters;
    }

    public void setMoca_isFinished_letters(Boolean moca_isFinished_letters) {
        this.moca_isFinished_letters = moca_isFinished_letters;
    }

    public Date getGo_date_response() {
        return go_date_response;
    }

    public void setGo_date_response(Date go_date_response) {
        this.go_date_response = go_date_response;
    }

    public Boolean getGo_isFinished() {
        return go_isFinished;
    }

    public void setGo_isFinished(Boolean go_isFinished) {
        this.go_isFinished = go_isFinished;
    }

    public String getGo_stimulus() {
        return go_stimulus;
    }

    public void setGo_stimulus(String go_stimulus) {
        this.go_stimulus = go_stimulus;
    }

    public ArrayList<ArrayList<String>> getGo_selected_letters() {
        return go_selected_letters;
    }

    public void setGo_selected_letters(ArrayList<ArrayList<String>> go_selected_letters) {
        this.go_selected_letters = go_selected_letters;
    }

    public ArrayList<ArrayList<String>> getGo_answers_letters() {
        return go_answers_letters;
    }

    public void setGo_answers_letters(ArrayList<ArrayList<String>> go_answers_letters) {
        this.go_answers_letters = go_answers_letters;
    }

    public ArrayList<Boolean> getGo_selected_stimulus() {
        return go_selected_stimulus;
    }

    public void setGo_selected_stimulus(ArrayList<Boolean> go_selected_stimulus) {
        this.go_selected_stimulus = go_selected_stimulus;
    }

    public ArrayList<Boolean> getGo_answers_stimulus() {
        return go_answers_stimulus;
    }

    public void setGo_answers_stimulus(ArrayList<Boolean> go_answers_stimulus) {
        this.go_answers_stimulus = go_answers_stimulus;
    }

    public ArrayList<Long> getGo_time_response() {
        return go_time_response;
    }

    public void setGo_time_response(ArrayList<Long> go_time_response) {
        this.go_time_response = go_time_response;
    }

    public long getGo_time_response_total() {
        return go_time_response_total;
    }

    public void setGo_time_response_total(long go_time_response_total) {
        this.go_time_response_total = go_time_response_total;
    }

    public int getGo_score() {
        return go_score;
    }

    public void setGo_score(int go_score) {
        this.go_score = go_score;
    }

    public Date getCatest_date_response() {
        return catest_date_response;
    }

    public void setCatest_date_response(Date catest_date_response) {
        this.catest_date_response = catest_date_response;
    }

    public Boolean getCatest_isFinished() {
        return catest_isFinished;
    }

    public void setCatest_isFinished(Boolean catest_isFinished) {
        this.catest_isFinished = catest_isFinished;
    }

    public ArrayList<String> getCatest_selected_words() {
        return catest_selected_words;
    }

    public void setCatest_selected_words(ArrayList<String> catest_selected_words) {
        this.catest_selected_words = catest_selected_words;
    }

    public ArrayList<ArrayList<String>> getCatest_selected_words_noise() {
        return catest_selected_words_noise;
    }

    public void setCatest_selected_words_noise(ArrayList<ArrayList<String>> catest_selected_words_noise) {
        this.catest_selected_words_noise = catest_selected_words_noise;
    }

    public ArrayList<String> getCatest_answers_words() {
        return catest_answers_words;
    }

    public void setCatest_answers_words(ArrayList<String> catest_answers_words) {
        this.catest_answers_words = catest_answers_words;
    }

    public int getCatest_score_words() {
        return catest_score_words;
    }

    public void setCatest_score_words(int catest_score_words) {
        this.catest_score_words = catest_score_words;
    }

    public int getCatest_selector_words() {
        return catest_selector_words;
    }

    public void setCatest_selector_words(int catest_selector_words) {
        this.catest_selector_words = catest_selector_words;
    }

    public ArrayList<Long> getCatest_time_response_words() {
        return catest_time_response_words;
    }

    public void setCatest_time_response_words(ArrayList<Long> catest_time_response_words) {
        this.catest_time_response_words = catest_time_response_words;
    }

    public long getCatest_time_response_words_total() {
        return catest_time_response_words_total;
    }

    public void setCatest_time_response_words_total(long catest_time_response_words_total) {
        this.catest_time_response_words_total = catest_time_response_words_total;
    }

    public Boolean getCatest_isFinished_words() {
        return catest_isFinished_words;
    }

    public void setCatest_isFinished_words(Boolean catest_isFinished_words) {
        this.catest_isFinished_words = catest_isFinished_words;
    }

    public String getCatest_selected_speech_stimulus() {
        return catest_selected_speech_stimulus;
    }

    public void setCatest_selected_speech_stimulus(String catest_selected_speech_stimulus) {
        this.catest_selected_speech_stimulus = catest_selected_speech_stimulus;
    }

    public ArrayList<String> getCatest_answers_speech_words() {
        return catest_answers_speech_words;
    }

    public void setCatest_answers_speech_words(ArrayList<String> catest_answers_speech_words) {
        this.catest_answers_speech_words = catest_answers_speech_words;
    }

    public Date getCatest_date_response_speech() {
        return catest_date_response_speech;
    }

    public void setCatest_date_response_speech(Date catest_date_response_speech) {
        this.catest_date_response_speech = catest_date_response_speech;
    }

    public Boolean getCatest_isFinished_speech() {
        return catest_isFinished_speech;
    }

    public void setCatest_isFinished_speech(Boolean catest_isFinished_speech) {
        this.catest_isFinished_speech = catest_isFinished_speech;
    }

    public String getCatest_score_speech() {
        return catest_score_speech;
    }

    public void setCatest_score_speech(String catest_score_speech) {
        this.catest_score_speech = catest_score_speech;
    }
}