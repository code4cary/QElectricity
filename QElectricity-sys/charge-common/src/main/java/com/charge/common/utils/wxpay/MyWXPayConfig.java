package com.charge.common.utils.wxpay;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;

@Configuration
@ConfigurationProperties(prefix = "wx.pay")
public class MyWXPayConfig extends WXPayConfig {

    private byte[] certData;

    private String appId;
    private String mchId;
    private String key;
    private String certPath;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppID() {
        return this.appId;
    }

    public String getMchID() {
        return this.mchId;
    }

    public String getKey() {
        return this.key;
    }
    public MyWXPayConfig() throws Exception {

    }

    //初始化证书信息
    @PostConstruct
    public void initCert() throws Exception {
        if(StringUtils.isEmpty(certPath)){
            return;
        }
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }




    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;

    }
}