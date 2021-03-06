package com.xabber.xmpp.smack;

import org.jivesoftware.smack.ConnectionConfiguration;

/**
 * A connection configuration for XMPP connections over TCP (the common case).
 * <p>
 * You can get an instance of the configuration builder with {@link #builder()} and build the final immutable connection
 * configuration with {@link org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder#build()}.
 * </p>
 * <pre>
 * {@code
 * XMPPTCPConnectionConfiguration conf = XMPPConnectionConfiguration.builder()
 *     .setXmppDomain("example.org").setUsernameAndPassword("user", "password")
 *     .setCompressionEnabled(false).build();
 * XMPPTCPConnection connection = new XMPPTCPConnection(conf);
 * }
 * </pre>
 */
public final class XMPPTCPConnectionConfiguration extends ConnectionConfiguration {

    /**
     * The default connect timeout in milliseconds. Preinitialized with 30000 (30 seconds). If this value is changed,
     * new Builder instances will use the new value as default.
     */
    public static int DEFAULT_CONNECT_TIMEOUT = 30000;

    private final boolean compressionEnabled;

    /**
     * How long the socket will wait until a TCP connection is established (in milliseconds).
     */
    private final int connectTimeout;

    private XMPPTCPConnectionConfiguration(Builder builder) {
        super(builder);
        compressionEnabled = builder.compressionEnabled;
        connectTimeout = builder.connectTimeout;
    }

    /**
     * Returns true if the connection is going to use stream compression. Stream compression
     * will be requested after TLS was established (if TLS was enabled) and only if the server
     * offered stream compression. With stream compression network traffic can be reduced
     * up to 90%. By default compression is disabled.
     *
     * @return true if the connection is going to use stream compression.
     */
    @Override
    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }

    /**
     * How long the socket will wait until a TCP connection is established (in milliseconds). Defaults to {@link #DEFAULT_CONNECT_TIMEOUT}.
     *
     * @return the timeout value in milliseconds.
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A configuration builder for XMPP connections over TCP. Use {@link org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration#builder()} to
     * obtain a new instance and {@link #build} to build the configuration.
     */
    public static final class Builder extends ConnectionConfiguration.Builder<Builder, XMPPTCPConnectionConfiguration> {
        private boolean compressionEnabled = false;
        private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;

        private Builder() {
        }

        /**
         * Sets if the connection is going to use stream compression. Stream compression
         * will be requested after TLS was established (if TLS was enabled) and only if the server
         * offered stream compression. With stream compression network traffic can be reduced
         * up to 90%. By default compression is disabled.
         *
         * @param compressionEnabled if the connection is going to use stream compression.
         * @return a reference to this object.
         */
        public Builder setCompressionEnabled(boolean compressionEnabled) {
            this.compressionEnabled = compressionEnabled;
            return this;
        }

        /**
         * Set how long the socket will wait until a TCP connection is established (in milliseconds).
         *
         * @param connectTimeout the timeout value to be used in milliseconds.
         * @return a reference to this object.
         */
        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        public XMPPTCPConnectionConfiguration build() {
            return new XMPPTCPConnectionConfiguration(this);
        }
    }
}
