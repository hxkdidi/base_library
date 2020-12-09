package com.lptiyu.lp_base.uitls;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * @author HansChen
 */
public class SensorUtils {

    /**
     * @return 是否支持计步功能
     */
    public static boolean isStepCountSupport(Context context) {
        return false;
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            return false;
//        }
//        String s = StringUtils.replaceSpaceWithDownLine(AppData.getMobileModel());
//        if (TextUtils.equals("m3_note", s) || TextUtils.equals("M5_Note", s) || TextUtils.equals("M621C", s)) {
//            return false;
//        }
//        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
//        PackageManager packageManager = context.getPackageManager();
//        return packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)
//                && packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)
//                && sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null
//                && sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null;
    }

    /**
     * @return 是否支持陀螺仪功能
     */
    public static boolean isGyroScopeSensorSupport(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)
                && sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null;
    }
}
