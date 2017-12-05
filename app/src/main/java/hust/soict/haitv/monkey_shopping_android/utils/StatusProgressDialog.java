package hust.soict.haitv.monkey_shopping_android.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by haitv on 05-12-2017.
 */

public class StatusProgressDialog {

    private static final String OK = "The request has succeeded"; // 200
    private static final String BAD_REQUEST = "Bad Request"; // 400
    private static final String NOT_AUTHORIZED = "Not Authorized"; // 401
    private static final String NO_TOKEN = "No token provided"; // 403
    private static final String NOT_FOUND = "Not Found"; // 404
    private static final String ERROR_SERVER = "Error on the server"; // 500
    private static final String UNKNOWN_ERROR = "An unknown error"; // Default

    private String message;

    public StatusProgressDialog(int code, Context context) {
        switch (code){
            case 400:
                message = BAD_REQUEST;
                break;
            case 401:
                message = NOT_AUTHORIZED;
                break;
            case 403:
                message = NO_TOKEN;
                break;
            case 404:
                message = NOT_FOUND;
                break;
            case 500:
                message = ERROR_SERVER;
                break;
            default:
                message = UNKNOWN_ERROR;
        }

        // Show Dialog:
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error message:");
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
