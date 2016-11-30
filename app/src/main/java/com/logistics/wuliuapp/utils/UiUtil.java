package com.logistics.wuliuapp.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YH
 */
public class UiUtil {

    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable mRunnable = new Runnable() {
        public void run() {
            if (mToast != null) {
                mToast.cancel();
                mToast = null;              // toast 隐藏后，将其置为 null
            }
        }
    };

    /**
     * 显示toast信息
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);// 居中显示 
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 显示toast信息
     *
     * @param context
     * @param message
     */
    public static void showToastLong(Context context, String message) {
        mHandler.removeCallbacks(mRunnable);
        if (mToast == null) {           // 只有 mToast == null 时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);// 居中显示 
        mToast.setText(message);
        mHandler.postDelayed(mRunnable, 2000);  // 延迟 duration 事件隐藏 toast
        mToast.show();
    }

    /**
     * @throws 抖动并提示
     */
    // public static void shake(Context context, View view) {
    //
    // // 动画---闪动效果
    // Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
    // view.startAnimation(shake);
    // }

    /**
     * 回调接口
     *
     * @author YF
     * @date 2014-7-23
     */
    public interface OnSetTitleListener {

        /**
         * 点击事件
         *
         * @param view ： 返回的控件
         */
        public void onClick(View view);

    }

    /**
     * 检验电话是否合法
     *
     * @param Str
     * @return
     */
    public static boolean checkPhone(String Str) {
        Pattern p = null;
        p = Pattern
                .compile("(^[0-9]{3,4}[0-9]{7,8}$)|(^[0-9]{3,4}-[0-9]{7,8}$)|^(13[0-9]|14[5|7]|15[0-9]|18[0-9])\\d{8}$");
        Matcher m = p.matcher(Str);
        if (!m.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断是不是正确的电话号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    public static boolean isValidMobiNumber(String paramString) {
        String regex = "^1\\d{10}$";
        if (paramString.matches(regex)) {
            return true;
        }
        return false;
    }

    public static boolean isValidName(String paramString) {

        String regex = "[a-zA-Z0-9_]{1,}";
        if (paramString.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidEmail(String paramString) {

        String regex = "[a-zA-Z0-9_\\.]{1,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        if (paramString.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取裁剪后的圆形图片
     *
     * @param radius 半径
     */
    public static Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
        Bitmap scaledSrcBmp;
        int diameter = radius * 2;

        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else {
            squareBitmap = bmp;
        }

        if (squareBitmap.getWidth() != diameter
                || squareBitmap.getHeight() != diameter) {
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,
                    diameter, true);

        } else {
            scaledSrcBmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
                scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,
                paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
        // bitmap回收(recycle导致在布局文件XML看不到效果)
        // bmp.recycle();
        // squareBitmap.recycle();
        // scaledSrcBmp.recycle();
        bmp = null;
        squareBitmap = null;
        scaledSrcBmp = null;
        return output;
    }

    /**
     * 设置圆角图片
     *
     * @param bitmap
     * @param pixels 30
     * @return
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels * bitmap.getWidth() / 160;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 为图片添加相框
     *
     * @param frame
     * @param src
     * @param x
     * @param y
     * @return
     */
    public static Bitmap montageBitmap(Bitmap frame, Bitmap src, int x, int y) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap sizeFrame = Bitmap.createScaledBitmap(frame, w, h, true);

        Bitmap newBM = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(newBM);
        canvas.drawBitmap(src, x, y, null);
        canvas.drawBitmap(sizeFrame, 0, 0, null);
        return newBM;
    }

    /**
     * 叠加边框图片有用部分
     *
     * @param bmp
     * @return
     */
    private static Bitmap alphaLayer(Bitmap bmp, Context context, int drawable) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Config.RGB_565);

        // 边框图片
        Bitmap overlay = BitmapFactory.decodeResource(context.getResources(),
                drawable);
        int w = overlay.getWidth();
        int h = overlay.getHeight();
        float scaleX = width * 1F / w;
        float scaleY = height * 1F / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        Bitmap overlayCopy = Bitmap.createBitmap(overlay, 0, 0, w, h, matrix,
                true);

        int pixColor = 0;
        int layColor = 0;
        int newColor = 0;

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int pixA = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;
        int newA = 0;

        int layR = 0;
        int layG = 0;
        int layB = 0;
        int layA = 0;

        float alpha = 0.3F;
        float alphaR = 0F;
        float alphaG = 0F;
        float alphaB = 0F;
        for (int i = 0; i < width; i++) {
            for (int k = 0; k < height; k++) {
                pixColor = bmp.getPixel(i, k);
                layColor = overlayCopy.getPixel(i, k);

                // 获取原图片的RGBA值
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                pixA = Color.alpha(pixColor);

                // 获取边框图片的RGBA值
                layR = Color.red(layColor);
                layG = Color.green(layColor);
                layB = Color.blue(layColor);
                layA = Color.alpha(layColor);

                // 颜色与纯黑色相近的点
                if (layR < 20 && layG < 20 && layB < 20) {
                    alpha = 1F;
                } else {
                    alpha = 0.3F;
                }

                alphaR = alpha;
                alphaG = alpha;
                alphaB = alpha;

                // 两种颜色叠加
                newR = (int) (pixR * alphaR + layR * (1 - alphaR));
                newG = (int) (pixG * alphaG + layG * (1 - alphaG));
                newB = (int) (pixB * alphaB + layB * (1 - alphaB));
                layA = (int) (pixA * alpha + layA * (1 - alpha));

                // 值在0~255之间
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                newA = Math.min(255, Math.max(0, layA));

                newColor = Color.argb(newA, newR, newG, newB);
                bitmap.setPixel(i, k, newColor);
            }
        }

        return bitmap;
    }

    /**
     * 实现文本复制功能
     *
     * @param content
     */
    @SuppressWarnings("deprecation")
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    /**
     * 检查文字是否为空
     *
     * @param text
     * @return
     */
    public static boolean IsValueEmpty(String text) {
        if (text != null && text.trim().length() > 0
                && !text.toLowerCase().equals("null")) {
            return false;
        }
        return true;
    }

    public static String numToString(int num) {
        String numString = "";
        switch (num) {
            case 0:
                numString = "零";
                break;
            case 1:
                numString = "一";
                break;
            case 2:
                numString = "二";
                break;
            case 3:
                numString = "三";
                break;
            case 4:
                numString = "四";
                break;
            case 5:
                numString = "五";
                break;
            case 6:
                numString = "六";
                break;
            case 7:
                numString = "七";
                break;
            case 8:
                numString = "八";
                break;
            case 9:
                numString = "九";
                break;
            case 10:
                numString = "十";
                break;
            default:
                break;
        }
        return numString;
    }
}
