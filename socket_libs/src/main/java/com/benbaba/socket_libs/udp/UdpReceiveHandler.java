package com.benbaba.socket_libs.udp;

import android.util.Log;

import com.benbaba.socket_libs.Constants;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class UdpReceiveHandler extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        if (session != null) {
            session.close(true);
        }
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        Log.i(Constants.TAG, "sessionCreated");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
//        super.messageReceived(session, message);
        Log.i(Constants.TAG, "message:" + message.toString());
        //获取发送端发送过来得IP地址
        String remoteIp = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        Log.i(Constants.TAG, "sessionCreated remoteIp:" + remoteIp);
    }

}
