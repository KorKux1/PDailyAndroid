package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import co.edu.icesi.pdailyandroid.R;


public class NotificationPermissionFragment extends Fragment {

    private AlertDialog permissionDialog;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification_permission, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        boolean areNotificationsEnabled = notificationManager.areNotificationsEnabled();

        if (areNotificationsEnabled) {
            if (permissionDialog != null && permissionDialog.isShowing()) {
                permissionDialog.dismiss();
            }
            // Cerrar este fragmento y regresar al anterior (o manejar como desees)
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        } else {
            showNotificationPermissionDialog();
        }
    }


    private void showNotificationPermissionDialog() {
        permissionDialog = new AlertDialog.Builder(getContext())
                .setTitle("Permisos de Notificación")
                .setMessage("Esta aplicación requiere permisos de notificación para funcionar correctamente. ¿Desea habilitarlos?")
                .setPositiveButton("Habilitar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                .putExtra(Settings.EXTRA_APP_PACKAGE, getContext().getPackageName());
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cerramos el fragmento y regresamos a MainActivity
                        if (getFragmentManager() != null) {
                            getFragmentManager().popBackStack();
                        }
                    }
                })
                .setCancelable(false)
                .show();

    }


}
