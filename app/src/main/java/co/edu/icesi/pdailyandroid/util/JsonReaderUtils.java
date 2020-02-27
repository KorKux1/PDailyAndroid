package co.edu.icesi.pdailyandroid.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class JsonReaderUtils {

     public static String getJsonFromAssets(Context context, String form_name) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(form_name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }


}
