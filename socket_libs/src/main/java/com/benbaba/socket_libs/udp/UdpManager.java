package com.benbaba.socket_libs.udp;

import android.util.Log;

import com.benbaba.socket_libs.Constants;
import com.benbaba.socket_libs.utils.IoUtils;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.ExpiringSessionRecycler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.DatagramAcceptor;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/**
 * Udp得管理类
 */
public class UdpManager {

    private static UdpManager mInstance;
    private InnerThread innerThread; //内部线程
    private UdpConfig udpConfig; // udp得配置类
    private NioDatagramAcceptor acceptor;

    /**
     * 获取单例对象
     *
     * @return
     */
    public static UdpManager getInstance() {
        if (mInstance == null) {
            synchronized (UdpManager.class) {
                if (mInstance == null) {
                    mInstance = new UdpManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 开启Udp得接收器
     */
    public void startReceiveUdp() {
        if (udpConfig == null || udpConfig.bindPort == 0) {
            throw new RuntimeException("请实例化UdpConfig并配置UdpConfig得bindPort");
        }
        if (innerThread == null || !innerThread.isAlive()) {
            //启动线程
            innerThread = new InnerThread();
            innerThread.start();
        } else {
            //唤醒线程
            innerThread.notify();
        }
    }

    /**
     * 停止UDP得接收器
     */
    public void stopReceiveUdp() {
        if (innerThread != null) {
            if (acceptor != null) {
                acceptor.dispose();
                acceptor.unbind();
                acceptor = null;
            }
            innerThread.interrupt();
            innerThread = null;
        }
    }

    /**
     * 设置udp得配置类
     *
     * @param config
     */
    public void setUdpConfig(UdpConfig config) {
        this.udpConfig = config;
    }

    /**
     * @param sendIp   发送得ip地址
     * @param sendPort 发送得端口号
     * @param sendMsg  发送得消息
     */
    public boolean sendMsg(String sendIp, int sendPort, String sendMsg) {
        if (acceptor == null) {
            throw new RuntimeException("请首先startReceiveUdp()才能发送消息");
        }
        InetSocketAddress remoteAddress = new InetSocketAddress(sendIp, sendPort);
        InetSocketAddress localAddress = new InetSocketAddress(udpConfig.bindPort);
        IoSession session = acceptor.newSession(remoteAddress, localAddress);
        byte[] data = sendMsg.getBytes();
        IoBuffer buffer = IoBuffer.allocate(data.length);
        buffer.put(data);
        buffer.flip();
        WriteFuture future = session.write(sendMsg, remoteAddress);
        future.awaitUninterruptibly(100);
        Log.i(Constants.TAG, "往ip:" + sendIp + ":" + sendPort + "发送消息成功：" + sendMsg);
        if (future.isWritten()) {
            session.close(true);
            return true;
        } else {
            session.close(true);
            return false;
        }
    }

    /**
     * 内部用于接收消息得Udp线程
     */
    private class InnerThread extends Thread {
        @Override
        public void run() {
            if (acceptor == null || acceptor.isDisposed()) {
                acceptor = new NioDatagramAcceptor();
                acceptor.getFilterChain()
                        .addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
                acceptor.getFilterChain().addLast(
                        "codec",
                        new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                                LineDelimiter.WINDOWS.getValue(),
                                LineDelimiter.WINDOWS.getValue())));
                acceptor.setHandler(new UdpReceiveHandler());
                acceptor.setSessionRecycler(new ExpiringSessionRecycler(udpConfig.sessionDelay));
                DatagramSessionConfig dsc = acceptor.getSessionConfig();
                dsc.setReuseAddress(true);
                dsc.setBroadcast(true);
                dsc.setReceiveBufferSize(udpConfig.receiveBufferSize);
                dsc.setSendBufferSize(udpConfig.sendBufferSize);
            }
            try {
                acceptor.bind(new InetSocketAddress(udpConfig.bindPort));
                Log.i(Constants.TAG, "mina bind success");
            } catch (IOException e) {
                Log.i(Constants.TAG, "mina bind exception");
                e.printStackTrace();
            }
            super.run();
        }
    }

}
