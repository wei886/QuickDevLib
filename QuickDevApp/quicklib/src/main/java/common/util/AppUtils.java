package common.util;

import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import app.BaseApplication;


/**
  * 用于存放一些常见的杂乱的工具方法
 */
public class AppUtils {

    private static AppUtils mInstance = new AppUtils();

    public static AppUtils getInstance() {
        return mInstance;
    }


    private AppUtils() {
    }

    /**
     * 获取设备型号
     *
     * @return
     */
    public String getDeviceName() {
        String device_model = Build.MODEL; // 设备型号
        if (TextUtils.isEmpty(device_model)) {
            LUtils.e("获取设备型号失败！Build.MODEL为空");
        }
        return device_model == null ? "未知设备" : device_model;
    }

    /**
     * 获取设备号
     * TODO 电话设备无法获取,需要优化，接锅！
     *
     * @return
     */
    public String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) BaseApplication.mContext.getSystemService(
                BaseApplication.mContext.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(imei)) {
            //非电话设备无法获取getDeviceId
            LUtils.e("获取设备号失败！DeviceId为空");
            imei = Build.SERIAL;
            if (TextUtils.isEmpty(imei)) {
                imei = Settings.System.getString(BaseApplication.mContext.getContentResolver(), Settings.System.ANDROID_ID);
            }
        }
        return imei == null ? "UNKNOWN_DEVICE_ID" : imei;
    }


}
