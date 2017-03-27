package common.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SDCardUtils {

    private static String Tag = "SDCardUtils";


    public static final String PACKNAME_NAME = "wh_test";
    public static final String Picture_suffix = ".png";


    /**
     * 获取手机插入的外置SD卡路径
     *
     * @param context
     * @return
     */
    public static String getExternSDCardPath(Context context) {
        return getExternSdPath(context, true);
    }


    /**
     * 获取手机内置SD卡路径
     * /storage/sdcard0
     *
     * @return
     */
    public static String getInternalSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }


    /**
     * 手机内部存储的路径 (一般不用)
     * Environment.getDataDirectory() 	/data
     * Environment.getDownloadCacheDirectory() 	/cache
     * Environment.getRootDirectory() 	/system
     *
     * @return
     */
    public static String getInternalStoragePath() {
        return Environment.getDataDirectory().getAbsolutePath() + File.separator;
    }


    /**
     * 获取手机插入的外置SD卡路径
     *
     * @param mContext
     * @param is_removale
     * @return
     */
    private static String getExternSdPath(Context mContext, boolean is_removale) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 判断内置SDCard是否挂载
     *
     * @return
     */
    public static boolean isInternalSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }


    /**
     * 获取内置SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isInternalSDCardEnable()) {
            StatFs stat = new StatFs(getInternalSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getInternalSDCardPath())) {
            filePath = getInternalSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }


    /*************************************************************************************
     Environment.getExternalStoragePublicDirectory(DIRECTORY_ALARMS) 	/storage/sdcard0/Alarms
     Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM) 	/storage/sdcard0/DCIM
     Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS) 	/storage/sdcard0/Download
     Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES) 	/storage/sdcard0/Movies
     Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC) 	/storage/sdcard0/Music
     Environment.getExternalStoragePublicDirectory(DIRECTORY_NOTIFICATIONS) 	/storage/sdcard0/Notifications
     Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES) 	/storage/sdcard0/Pictures
     Environment.getExternalStoragePublicDirectory(DIRECTORY_PODCASTS) 	/storage/sdcard0/Podcasts
     Environment.getExternalStoragePublicDirectory(DIRECTORY_RINGTONES) 	/storage/sdcard0/Ringtones
     *****************************************************************************************/


    /**
     * @return String
     * @Title: getCachePath
     * @Description: 获取应用的cache目录
     */
    public static String getInternalCachePath(Context context) {
        File f = context.getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + File.separator;
        }
    }


    /**
     * 获取图片下载的路径
     *
     * @param context
     * @return
     */
    public static String getImageLoadedPath(Context context, String fileName) {

        StringBuilder sb = new StringBuilder();

        String path = "";
        String internalPath = "";

        if (isInternalSDCardEnable()) {
            internalPath = getInternalSDCardPath();
        } else {
            internalPath = getInternalCachePath(context);
        }

        if (!TextUtils.isEmpty(internalPath)) {  //内置sd卡路径
            path = internalPath + PACKNAME_NAME + File.separator;
        } else { //外置sd卡路径
            path = getExternSDCardPath(context);
        }
        createDirs(path);

        Log.e("wh","path -- =" +path);

        path += fileName + Picture_suffix;
        return path;
    }

    public static boolean createDirs(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return false;
        }
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }


}
