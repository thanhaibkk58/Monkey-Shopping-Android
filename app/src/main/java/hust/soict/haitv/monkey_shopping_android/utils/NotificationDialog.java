package hust.soict.haitv.monkey_shopping_android.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by haitv on 05-12-2017.
 */

public class NotificationDialog {

    public NotificationDialog(String message, Context context) {
        // Show Dialog:
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Notification message:");
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
