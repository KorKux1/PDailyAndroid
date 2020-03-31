package co.edu.icesi.pdailyandroid.cognosis.temporal;

import java.util.ArrayList;

public class TestTemporal {


    //SINGLETON CONFIG
    private static TestTemporal instance;

    public static TestTemporal getInstance(){
        if(instance == null){
            instance = new TestTemporal();
        }
        return instance;
    }

    private TestTemporal(){
        answers = new ArrayList<>();
    }

    private ArrayList<String> answers;

    //Getter
    public ArrayList<String> getAnswers() {
        return answers;
    }

}
