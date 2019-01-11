package com.bhx.library.ui;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.benbaba.socket_libs.XSocketWork;
import com.benbaba.socket_libs.udp.UdpConfig;
import com.benbaba.socket_libs.udp.UdpManager;
import com.bhx.library.R;
import com.bhx.library.network.NetWorkManager;
import com.bhx.library.network.annotation.NetWork;
import com.bhx.library.network.bean.NetType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        NetWorkManager.getInstance().registerObserver(this);

        UdpConfig udpConfig = new UdpConfig.Builder()
                .setBindPort(10025).build();

        UdpManager.getInstance().setUdpConfig(udpConfig);
        UdpManager.getInstance().startReceiveUdp();

        findViewById(R.id.id_send)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        UdpManager.getInstance().sendMsg("255.255.255.255", 10026, "sssssssssssss");
                    }
                });
    }
//
//    @NetWork(type = NetType.WIFI)
//    public void onWifi(NetType netType) {
//        switch (netType) {
//            case NONE:
//                Toast.makeText(this, "网络连接断开", Toast.LENGTH_SHORT).show();
//                break;
//            case WIFI:
//                Toast.makeText(this, "当前是wifi环境", Toast.LENGTH_SHORT).show();
//                break;
//            case CMNET:
//            case CMWAP:
//                Toast.makeText(this, "当前是4g环境", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        NetWorkManager.getInstance().unRegisterObserver(this);
//    }
}
