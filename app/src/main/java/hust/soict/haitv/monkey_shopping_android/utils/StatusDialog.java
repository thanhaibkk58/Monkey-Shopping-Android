package hust.soict.haitv.monkey_shopping_android.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by haitv on 05-12-2017.
 */

public class StatusDialog {

    ProgressDialog progress;

    public StatusDialog(String message, Context context) {
        progress = new ProgressDialog(context);
        progress.setMessage(message);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
    }

    public void showStatusDialog(){
        progress.show();
    }

    public void dismissStatusDialog(){
        progress.dismiss();
    }
}
