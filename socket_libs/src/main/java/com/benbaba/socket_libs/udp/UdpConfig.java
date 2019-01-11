package com.benbaba.socket_libs.udp;

/**
 * udp配置类
 */
public class UdpConfig {
    private static final int RECEIVE_BUFFER_SIZE = 1024;
    private static final int SEND_BUFFER_SIZE = 1024;
    private static final int SESSION_DELAY = 15 * 1000;
    public int bindPort;//绑定得端口号
    public int receiveBufferSize; // 接收消息得缓存大小
    public int sendBufferSize; // 发送消息得缓存大小
    public int sessionDelay;// 会话超时时间

    UdpConfig(int port, int receiveBufferSize, int sendBufferSize, int sessionDelay) {
        this.bindPort = port;
        this.receiveBufferSize = receiveBufferSize;
        this.sendBufferSize = sendBufferSize;
        this.sessionDelay = sessionDelay;
    }

    public static class Builder {

        private int bindPort;
        private int receiveBufferSize = RECEIVE_BUFFER_SIZE;
        private int sendBufferSize = SEND_BUFFER_SIZE;
        private int sessionDelay = SESSION_DELAY;


        public Builder setBindPort(int port) {
            this.bindPort = port;
            return this;
        }

        public Builder setReceiveBufferSize(int receiveBufferSize) {
            this.receiveBufferSize = receiveBufferSize;
            return this;
        }

        public Builder setSendBufferSize(int sendBufferSize) {
            this.sendBufferSize = sendBufferSize;
            return this;
        }

        public Builder setSessionDelay(int sessionDelay) {
            this.sessionDelay = sessionDelay;
            return this;
        }

        public UdpConfig build() {
            return new UdpConfig(bindPort, receiveBufferSize, sendBufferSize, sessionDelay);
        }

    }


}
