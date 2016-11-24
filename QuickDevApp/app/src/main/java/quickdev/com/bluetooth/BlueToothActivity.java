package quickdev.com.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Set;

import common.ToastUtils;
import quickdev.com.quick.R;

public class BlueToothActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvContent;

    private Context context = BlueToothActivity.this;
    private TextView mTvContentUsableDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);

        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.btn_setvisiabletime).setOnClickListener(this);
        findViewById(R.id.btn_scanuseabledevice).setOnClickListener(this);

        mTvContent = (TextView) findViewById(R.id.tv_content_has_peidui);
        mTvContentUsableDevice = (TextView) findViewById(R.id.tv_content_usable_device);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                scan_haspeidui();
                break;
            case R.id.btn_setvisiabletime:
                setDeviceVisiableTime();
                break;
            case R.id.btn_scanuseabledevice:
                scanusabledevice();
                break;
        }
    }


    /**
     * 扫描 周围蓝牙设备的mac地址
     */
    private void scan_haspeidui() {

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter != null) {

            if (bluetoothAdapter.isEnabled()) {


                Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
                String string = "已经配对的:\n";
                if (bondedDevices.size() > 0) {
                    for (Iterator iterator = bondedDevices.iterator(); iterator.hasNext(); ) {
                        BluetoothDevice next = (BluetoothDevice) iterator.next();
                        string += next + "\n";
                    }
                } else {
                    mTvContent.setText("0个设备");
                }
                mTvContent.setText(string);

            } else {
                startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
            }

        } else {
            ToastUtils.toast(context, "adapter null");
        }
    }

    /**
     * 设置蓝牙设备的可见性时间
     */
    private void setDeviceVisiableTime() {
        //创建一个Intent对象,并且将其action的值设置为BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE也就是蓝牙设备设置为可见状态
        Intent kejianxingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        //将一个键值对存放到Intent对象当中,主要用于指定可见状态的持续时间,大于300秒,就认为是300秒
        kejianxingIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 500);
        //这个Activity实际上是安卓自带的一个Activity
        startActivity(kejianxingIntent);
    }


    /**
     * -------------------------------------3扫描周围可用的且没配对的蓝牙设备---------------------------------------------
     */

    private void scanusabledevice() {

        //得到本机蓝牙设备
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //创建一个IntentFilter对象,将其action指定为BluetoothDevice.ACTION_FOUND
        //IntentFilter它是一个过滤器,只有符合过滤器的Intent才会被我们的BluetoothReceiver所接收
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        //创建一个BluetoothReceiver对象
        BluetoothReceiver bluetoothReceiver = new BluetoothReceiver();
        //注册广播接收器 注册完后每次发送广播后，BluetoothReceiver就可以接收到这个广播了
        registerReceiver(bluetoothReceiver, intentFilter);


    }

    //接收广播
    private class BluetoothReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //只要BluetoothReceiver接收到来自于系统的广播,这个广播是什么呢,是我找到了一个远程蓝牙设备
                //Intent代表刚刚发现远程蓝牙设备适配器的对象,可以从收到的Intent对象取出一些信息
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                 mTvContentUsableDevice.setText(mTvContentUsableDevice.getText() +"\n"+bluetoothDevice.getAddress());
            }
        }
    }


    /**-------------------------------------4--------------------------------------------- */


}
