package com.lenebf.demo.blibrary;

import android.app.Application;
import android.content.res.Resources;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author lenebf@126.com
 * @since 2020/9/23
 */
public class BrightnessUtils {
    private BrightnessUtils() {
    }

    public static int getSystemBrightnessMax() {
        try {
            Resources system = Resources.getSystem();
            int resId = system.getIdentifier("config_screenBrightnessSettingMaximum", "integer", "android");
            if (resId != 0) {
                return system.getInteger(resId);
            }
        } catch (Exception ignore) {
        }
        return 255;
    }

    public static int getSystemBrightness(Application app) {
        try {
            return Settings.System.getInt(
                    app.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS
            );
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param brightness 小于0表示跟随系统亮度
     */
    public static void setWindowBrightness(Window window, int brightness) {
        if (brightness == 0) {
            brightness = 1;
        }
        int maxBrightness = getSystemBrightnessMax();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = brightness * 1.0f / maxBrightness;
        window.setAttributes(lp);
    }
}
