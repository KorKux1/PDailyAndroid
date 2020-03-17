package co.edu.icesi.pdailyandroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;

public class DataHandler extends SQLiteOpenHelper {

    private static final String NAME = "PDaily";
    private static final int VERSION = 1;

    private static DataHandler instance = null;

    public static DataHandler getInstance(Context context){
        if(instance == null){
            instance = new DataHandler(context);
        }
        return instance;
    }

    private DataHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FOOD = "CREATE TABLE food(id TEXT PRIMARY KEY, name TEXT, date TEXT)";
        db.execSQL(CREATE_TABLE_FOOD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS food");
    }

    //---CRUD FOOD NOTIFICATIONS---//

    private void insertFoodNotification(NotificationFoodFollowUp notification){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO food(id,name,date) VALUES('"+notification.getId()+"','"+notification.getName()+"','"+notification.getDate()+"')");
        db.close();
    }

    private void updateFoodNotification(NotificationFoodFollowUp notification){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE food SET name='"+notification.getName()+"', date='"+notification.getDate()+"' WHERE id='"+notification.getId()+"'");
        db.close();
    }

    private boolean notificationExists(NotificationFoodFollowUp notification){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM food WHERE id='" + notification.getId() + "'",null);
        if(cursor.getCount()>=1){
            db.close();
            return true;
        }else{
            db.close();
            return false;
        }
    }

    private void deleteFoodNotification(NotificationFoodFollowUp notification) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM food WHERE id='"+notification.getId()+"'");
        db.close();
    }

    //***CRUD FOOD NOTIFICATIONS***//


    public ArrayList<NotificationFoodFollowUp> getAllFoodNotifications(){
        ArrayList<NotificationFoodFollowUp> out = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM food",null);
        if(cursor.moveToFirst()){
            do{
                out.add(new NotificationFoodFollowUp(
                        cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("date"))
                ));
            }while (cursor.moveToNext());
        }
        db.close();
        return out;
    }

    public void insertOrUpdateFoodNotification(NotificationFoodFollowUp notification){
        if( notificationExists(notification) ){
            updateFoodNotification(notification);
        }else{
            insertFoodNotification(notification);
        }
    }

    public int getNotificationCount() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM food",null);
        int count = cursor.getCount();
        db.close();
        return count;
    }

    public String getBlockOfNotifications() {
        ArrayList<NotificationFoodFollowUp> nots = getAllFoodNotifications();
        String out = "";
        for(int i=0 ; i<nots.size() ; i++){
            out += nots.get(i).getName()+"\n";
        }
        return out;
    }
}
