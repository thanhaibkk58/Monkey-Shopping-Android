package hust.soict.haitv.monkey_shopping_android.utils;

import android.view.View;

/**
 * Created by haitv on 30-11-2017.
 */

public interface ClickListener {
    void onClick(View child, int position);
    void onLongClick(View child, int position);
}
