package com.benbaba.socket_libs;

import android.util.Log;

import com.benbaba.socket_libs.udp.UdpClientHandler;
import com.benbaba.socket_libs.udp.UdpConfig;
import com.benbaba.socket_libs.udp.UdpManager;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import java.net.InetSocketAddress;

/**
 * 用于
 * 1.注册监听 取消注册监听
 * 2.创建UDP服务端
 * 3.发送UDP消息 队列
 * 4.连接TCP服务端 进行TCP通信得维护
 * 5.
 */
public class XSocketWork {

    private static volatile XSocketWork mInstance;

    /**
     * 获取全局唯一实例
     *
     * @return
     */
    public static XSocketWork getInstance() {
        if (mInstance == null) {
            synchronized (XSocketWork.class) {
                if (mInstance == null)
                    mInstance = new XSocketWork();
            }
        }
        return mInstance;
    }

    private XSocketWork() {
    }

    /**
     * 开启UDP得接受端
     */
    public void startUdpServer() {
        UdpManager.getInstance().startReceiveUdp();
    }

    /**
     * 停止UDP得服务
     */
    public void stopUdpServer() {
        UdpManager.getInstance().stopReceiveUdp();
    }

    /**
     * 设置udp得配置参数
     *
     * @param config
     */
    public void setUdpConfig(UdpConfig config) {
        UdpManager.getInstance().setUdpConfig(config);
    }


//    IoSession session;
//
//    /**
//     * 发送消息
//     */
//    public void sendMsg() {
//        new Thread() {
//            @Override
//            public void run() {
//                NioDatagramConnector connector = new NioDatagramConnector();
//                connector.getSessionConfig().setReuseAddress(true);
//                connector.getSessionConfig().setBroadcast(true);
//                connector.setHandler(new UdpClientHandler());
//                ConnectFuture connFuture = connector.connect(new InetSocketAddress(
//                        "192.168.0.255", 10026));
//                connFuture.awaitUninterruptibly();
//                connFuture.addListener(new IoFutureListener<ConnectFuture>() {
//                    public void operationComplete(ConnectFuture future) {
//                        if (future.isConnected()) {
//                            session = future.getSession();
//                            sendData();
//                        }
//                    }
//                });
//                super.run();
//            }
//        }.start();
//    }
//
//    private void sendData() {
//        long free = Runtime.getRuntime().freeMemory();
//        IoBuffer buffer = IoBuffer.allocate(8);
//        buffer.putLong(free);
//        buffer.flip();
//        WriteFuture future = session.write(buffer);
//        future.awaitUninterruptibly();
//        if (future.isWritten()) {
//            Log.i(Constants.TAG, "sendData success");
//        } else {
//            Log.i(Constants.TAG, "sendData error");
//        }
//    }
}
