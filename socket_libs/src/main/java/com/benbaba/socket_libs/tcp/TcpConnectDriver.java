package com.benbaba.socket_libs.tcp;

import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

/**
 * MINA框架实现TCP得连接驱动
 */
public class TcpConnectDriver {
    /**
     *
     */
    private NioDatagramAcceptor mAccepter;
    private static TcpConnectDriver mInstance;
    private String mServerIp; // 服务器IP
    private int mServerPort; // 服务器端口号
    private long mConnectTimeOut;// 连接超时时间
    private boolean isOpenHeartCheck;// 是否开启心跳检车

    public static TcpConnectDriver init() {
        if (mInstance == null) {
            synchronized (TcpConnectDriver.class) {
                if (mInstance == null)
                    mInstance = new TcpConnectDriver();
            }
        }
        return mInstance;
    }

    /**
     * 设置需要连接得ip和
     *
     * @param serverIp
     * @param serverPort
     * @return
     */
    public TcpConnectDriver setIPAndPort(String serverIp, int serverPort) {
        this.mServerIp = serverIp;
        this.mServerPort = serverPort;
        return this;
    }


}
