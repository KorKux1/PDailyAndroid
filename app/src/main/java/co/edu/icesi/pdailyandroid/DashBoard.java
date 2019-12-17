package co.edu.icesi.pdailyandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import co.edu.icesi.pdailyandroid.viewcontrollers.BinnacleFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.EventFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.FoodFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.LevoFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.OthersFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.ProfileFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.RoutineFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.SupportFragment;

public class DashBoard extends AppCompatActivity {

    public static final String FOOD_TOPIC = "pdaily-food-";


    private Button supportButton;
    private Button routineButton;
    private Button foodButton;
    private Button profileButton;
    private Button othersButton;
    private Button levoButton;
    private Button binButton;
    private Button eventsButton;
    private FrameLayout fragmentContainer;

    private Fragment actualControl;

    private Fragment binFragment;
    private Fragment levoFragment;
    private Fragment othersFragment;
    private Fragment profileFragment;

    private Fragment foodFragment;
    private Fragment routineFragment;
    private Fragment supportFragment;
    private Fragment eventFragment;

    private BroadcastReceiver updateUIReciver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(">>>", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        Log.e(">>>", "Token: "+token);

                    }
                });


        supportButton = findViewById(R.id.supportButton);
        routineButton= findViewById(R.id.routineButton);
        foodButton= findViewById(R.id.foodButton);
        profileButton= findViewById(R.id.profileButton);
        othersButton= findViewById(R.id.othersButton);
        levoButton= findViewById(R.id.levoButton);
        binButton= findViewById(R.id.binButton);
        eventsButton= findViewById(R.id.eventsButton);
        fragmentContainer= findViewById(R.id.frameLayout);

        binFragment = new BinnacleFragment();
        levoFragment = new LevoFragment();
        othersFragment = new OthersFragment();
        profileFragment = new ProfileFragment();

        foodFragment = new FoodFragment();
        routineFragment = new RoutineFragment();
        supportFragment = new SupportFragment();
        eventFragment = new EventFragment();

        analizeIntent();
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        sp.edit().putString("clienteid","1234567890").apply();


        IntentFilter filter = new IntentFilter();
        filter.addAction("com.hello.action");

        updateUIReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ( (BinnacleFragment) binFragment).updateTable();
            }
        };
        registerReceiver(updateUIReciver,filter);



        String clientId = this.getSharedPreferences("user", MODE_PRIVATE).getString("clienteid", null);
        FirebaseMessaging.getInstance().subscribeToTopic(FOOD_TOPIC+clientId)
        //FirebaseMessaging.getInstance().subscribeToTopic("1143848922/#")
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.e(">>>", "Fail");
                    } else {
                        Log.e(">>>", "Suscribed");
                    }
                });


    }

    private void analizeIntent() {
        if(getIntent().getExtras() != null){
            String fragment = getIntent().getExtras().getString("fragment");
            if(fragment != null) {
                if (fragment.equals("binnacle")) {
                    doDashboardAction(binButton);
                }
            }
        }
    }

    public void doDashboardAction(View sender) {

        if(sender.equals(binButton)) {
            loadEventFragment(binFragment);
        }else if(sender.equals(levoButton)){
            loadEventFragment(levoFragment);
        }else if(sender.equals(othersButton)){
            loadEventFragment(othersFragment);
        }else if(sender.equals(profileButton)){
            loadEventFragment(profileFragment);
        }else if(sender.equals(foodButton)){
            loadEventFragment(foodFragment);
        }else if(sender.equals(routineButton)){
            loadEventFragment(routineFragment);
        }else if(sender.equals(supportButton)){
            loadEventFragment(supportFragment);
        }else if(sender.equals(eventsButton)){
            loadEventFragment(eventFragment);
        }

        allIsUnselected();

        if(actualControl == null){
            return;
        }


        if(actualControl instanceof SupportFragment){
            supportButton.setBackgroundResource(R.drawable.apoyoactivo);
        }
        else if(actualControl instanceof RoutineFragment){
            routineButton.setBackgroundResource(R.drawable.rutinaactivo);
        }
        else if(actualControl instanceof FoodFragment){
            foodButton.setBackgroundResource(R.drawable.comidaactivo);
        }
        else if(actualControl instanceof ProfileFragment){

        }
        else if(actualControl instanceof OthersFragment){
            othersButton.setBackgroundResource(R.drawable.otrosactivo);
        }
        else if(actualControl instanceof LevoFragment){
            levoButton.setBackgroundResource(R.drawable.levodopaactivo);
        }
        else if(actualControl instanceof BinnacleFragment){
            binButton.setBackgroundResource(R.drawable.bitacoraactivo);
        }
        else if(actualControl instanceof EventFragment){
            eventsButton.setBackgroundResource(R.drawable.eventosactivo);
        }

    }


    public void loadEventFragment(Fragment fragment){

        if(actualControl == null){
            load(fragment);
            return;
        }

        if (actualControl.equals(fragment)) {
            unload();
            return;
        }else{
            unload();
            load(fragment);
        }
    }

    private void load(Fragment fragment){
        actualControl = fragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.frameLayout,actualControl);
        ft.commit();
    }

    private void unload(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.remove(actualControl);
        ft.commit();
        actualControl = null;
    }

    private void allIsUnselected(){
        supportButton.setBackgroundResource(R.drawable.apoyoinactivo);
        routineButton.setBackgroundResource(R.drawable.rutinainactivo);
        foodButton.setBackgroundResource(R.drawable.comidainactivo);
        //profileButton.setImage(UIImage(named: "apoyoinactivo"), for: .normal)
        othersButton.setBackgroundResource(R.drawable.otrosinactivo);
        levoButton.setBackgroundResource(R.drawable.levodopainactivo);
        binButton.setBackgroundResource(R.drawable.bitacorainactivo);
        eventsButton.setBackgroundResource(R.drawable.eventosinactivo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if( eventFragment.isVisible() ){
            ( (EventFragment) eventFragment).refreshList();
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(updateUIReciver);
        super.onDestroy();
    }
}
