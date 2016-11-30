package com.logistics.wuliuapp.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

public class DialogUtils {
    public static void setDialogSize(Activity context, float widthProportion, float heightProportion,
                                     View rootView) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenHeigh = dm.heightPixels;
        int screenWidth = dm.widthPixels;

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rootView
                .getLayoutParams();
        if (heightProportion >= 0) {
            layoutParams.height = (int) (screenHeigh * heightProportion);// 高度设置为屏幕高度比
        }
        layoutParams.width = (int) (screenWidth * widthProportion);// 宽度设置为屏幕的宽度比
        layoutParams.gravity = Gravity.CENTER;
        rootView.setLayoutParams(layoutParams);

    }
}
