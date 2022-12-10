package com.example.netstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.util.Log;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.util.Log;

public class NetWorkListener {

    private Context mContext;

    private NetWorkBroadcastReceiver receiver;

    private NetWorkStateListener mNetWorkLStateListener;

    public NetWorkListener(Context context) {
        mContext = context;
        receiver = new NetWorkBroadcastReceiver();
    }

    public void register(NetWorkStateListener listener) {
        mNetWorkLStateListener = listener;
        if (receiver != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

            mContext.registerReceiver(receiver, filter);
        }
    }

    public void unregister() {
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    private class NetWorkBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String acyion = intent.getAction();
                switch (acyion) {
                    case WifiManager.WIFI_STATE_CHANGED_ACTION:
                        if ( mNetWorkLStateListener != null) {
                            Log.d("WIFI", "NetWorkBroadcastReceiver --> onReceive--> WIFI_STATE_CHANGED_ACTION");
                            int i = mNetWorkLStateListener.onWIFIStateChanged();
                        }
                        break;
                }
            }        }
    }

    public interface NetWorkStateListener {
        int onWIFIStateChanged();

    }

}

