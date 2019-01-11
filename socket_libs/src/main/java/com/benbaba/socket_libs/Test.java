package com.benbaba.socket_libs;

import android.util.Log;

import com.benbaba.socket_libs.udp.UdpConfig;
import com.benbaba.socket_libs.udp.UdpManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Test {

    public static void main(String[] args) {
//        UdpConfig udpConfig = new UdpConfig.Builder()
//                .setBindPort(10025).build();
//        UdpManager.getInstance().setUdpConfig(udpConfig);
//        UdpManager.getInstance().startReceiveUdp();
//        DatagramSocket mSocket = null;
//        try {
//            byte[] mReceiveData = new byte[1024];
//            mSocket = new DatagramSocket(new InetSocketAddress(10025));
//            mSocket.setBroadcast(true);
//            mSocket.setReuseAddress(true);
//            DatagramPacket packet = new DatagramPacket(mReceiveData, 0, mReceiveData.length);
//            mSocket.receive(packet);
//            String receiveMsg = new String(mReceiveData, 0, packet.getLength());
//            String address = packet.getAddress().toString().substring(1);
//            System.out.println("receiveMsg:" + receiveMsg);
//            System.out.println("address:" + address);
//        }  catch (IOException e) {
//            e.printStackTrace();
//        }
        sendMsg("192.168.0.123","sssssssssssss");
    }
    public static void sendMsg(String serverIp, String msg) {
        try {
//            Log.i("TAG", "udp sendMsg....:");
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);
            socket.setReuseAddress(true);
            byte[] sendData = msg.getBytes();
            DatagramPacket send = new DatagramPacket(sendData, 0, sendData.length);
            send.setAddress(InetAddress.getByName(serverIp));
            send.setPort(10025);
            socket.send(send);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
