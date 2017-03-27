package common.util.ImageUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.util.SDCardUtils;
import common.util.ToastUtils;
import dev.com.quicklib.R;


public class ImageUtils {
    /**
     * 单线程列队执行
     */
    private static ExecutorService singleExecutor = null;
    private Context mContext;

    private ImageUtils(Context context) {
        mContext = context;
    }


    private static ImageUtils INSTANCE;


    public static ImageUtils instance(Context context) {
        if (null == INSTANCE) {
            synchronized (ImageUtils.class) {
                if (null == INSTANCE)
                    INSTANCE = new ImageUtils(context);
            }
        }
        return INSTANCE;
    }


    /**
     * 执行单线程列队执行
     */
    private void runOnQueue(Runnable runnable) {
        if (singleExecutor == null) {
            singleExecutor = Executors.newSingleThreadExecutor();
        }
        singleExecutor.submit(runnable);
    }


    public void disPlayImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).placeholder(R.drawable.picture_default).error(R.drawable.picture_error).into(imageView);
    }


    /**
     * 启动图片下载线程
     * 成功后：Glide.with(Activity.this).load(url).into(imageview);
     *
     * @param url 地址
     */
    public void onDownLoad(String url, final String fileName, final ImageCallBack imageCallBack) {
        DownLoadImageService service = new DownLoadImageService(mContext,
                url,
                new ImageDownLoadCallBack() {
                    @Override
                    public void onDownLoadSuccess(File file) {
                        final String desPath = SDCardUtils.getImageLoadedPath(mContext, fileName);
                        savePhotoToSDCard(file.getPath(), desPath, null, false);
                        ((Activity) mContext).runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                imageCallBack.onSuccess(desPath);
                            }
                        });

                    }

                    @Override
                    public void onDownLoadFailed() {
                        imageCallBack.onFailed();
                    }
                });
        //启动图片下载线程
        runOnQueue(service);
    }


    /**
     * @param url
     * @param fileName
     */
    public void downLoadImage(String url, final String fileName, final Context context) {
        DownLoadImageService service = new DownLoadImageService(mContext,
                url,
                new ImageDownLoadCallBack() {
                    @Override
                    public void onDownLoadSuccess(File file) {
                        final String desPath = SDCardUtils.getImageLoadedPath(mContext, fileName);
                        savePhotoToSDCard(file.getPath(), desPath, context, true);
                    }

                    @Override
                    public void onDownLoadFailed() {
                    }
                });
        //启动图片下载线程
        runOnQueue(service);
    }


    /**
     * 保存到SD卡
     *
     * @param path
     * @param desPath
     */
    private void savePhotoToSDCard(final String path, final String desPath, final Context context, boolean toast) {
        Bitmap bitmap = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                return;
            }
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, opts);
            opts.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(path, opts);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int options = 100;
            bitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
            File file2 = new File(desPath);
            FileOutputStream fOut = new FileOutputStream(file2);
            fOut.write(baos.toByteArray());
            fOut.flush();
            fOut.close();
            baos.flush();
            baos.close();
            bitmap.recycle();
            bitmap = null;
            if (null != context && toast && context instanceof Activity) {
                Activity activity = (Activity) context;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.toast(context, "图片保存成功" + desPath);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void clearCache(Context context) {
        Glide.get(context).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
        Glide.get(context).clearMemory();//清理内存缓存 可以在UI主线程中进行
    }
}
