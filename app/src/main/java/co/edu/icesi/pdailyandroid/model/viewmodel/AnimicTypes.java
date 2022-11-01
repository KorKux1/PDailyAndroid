package co.edu.icesi.pdailyandroid.model.viewmodel;

import java.util.HashMap;

public class AnimicTypes {


    private HashMap<Integer, String> animicLabels;
    private HashMap<Integer, String> animicTypes;


    public AnimicTypes() {
        animicLabels = new HashMap<>();
        animicLabels.put(9, "¡Muy mal!");
        animicLabels.put(8, "¡Mal!");
        animicLabels.put(7, "¡Regular!");
        animicLabels.put(6, "Los sintomas estan empezando a empeorar");
        animicLabels.put(5, "Neutral");
        animicLabels.put(4, "Siento mejora leve en los sintomas");
        animicLabels.put(3, "¡Bien! Algunos sintomas pero mejorando");
        animicLabels.put(2, "¡Muy bien! Casi no tengo sintomas");
        animicLabels.put(1, "¡Super bien! No hay sintomas");
        animicLabels.put(10, "EXISTENT");

        animicTypes = new HashMap<>();
        animicTypes.put(9, "661d90ae-88b8-4305-ba60-a4ba54bbf757");
        animicTypes.put(8, "3431b67a-3cb2-4fdb-a5a2-bb73f450b7ad");
        animicTypes.put(7, "6663f543-5735-468a-8464-2841a9741916");
        animicTypes.put(6, "77179f06-cacb-4211-bb72-bdd3ea1b859c");
        animicTypes.put(5, "65fec440-5460-4b9b-9bd4-03d5ee4ea240");
        animicTypes.put(4, "92989229-d349-4d3b-b52d-f3e4731061e4");
        animicTypes.put(3, "8c01d50b-782f-40b2-a32c-d5ce18abb1f2");
        animicTypes.put(2, "32e7ccd6-f9c7-4bf3-b438-0cb3cd5eb7f6");
        animicTypes.put(1, "ea78846b-c7af-4b7c-b4e9-09419c19fb7a");
        animicTypes.put(10, "022f4eb0-7c18-4eae-80a8-c33c80e39bc7");
    }

    public static AnimicTypes getReference() {
        return new AnimicTypes();
    }

    public String getLabelByScore(int score) {
        return animicLabels.get(score);
    }

    public String getTypeIDByScore(int score) {
        return animicTypes.get(score);
    }

}
