package com.poker.notification.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.PushPayload;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 客户端初始化
 *
 * @author run
 * @create 2017-11-08
 **/
public class JPushUtil {

    private static final Logger logger = Logger.getLogger(JPushUtil.class);

    private static JPushClient jPushClient;

    private JPushUtil() {
        initJPush();
    }

    private static final JPushUtil instance = new JPushUtil();

    public static final JPushUtil getInstance() {
        return instance;
    }

    private void initJPush() {
        ClientConfig clientConfig = ClientConfig.getInstance();
        //读取配置
        String isProduction = PropertieUtil.newBuilder("jpush").getProperty("jpush.isProduction");
        clientConfig.setApnsProduction(Boolean.getBoolean(isProduction));
        String appKey = PropertieUtil.newBuilder("jpush").getProperty("jpush.appKey");
        String masterSecret = PropertieUtil.newBuilder("jpush").getProperty("jpush.masterSecret");
        jPushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
    }

    public JPushClient getJPushClient() {
        return jPushClient;
    }

}