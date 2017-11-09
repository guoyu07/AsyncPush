package com.poker.notification.push;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import java.util.List;
import java.util.Map;

/**
 * 推送服务接口
 *
 * @author run
 * @create 2017-11-08
 **/
public interface PushService {
    void sendWithTag(String content, Platform platform, List<String> tags, Map extras, String scheduleFlag) throws APIConnectionException, APIRequestException;
    void send(String content, Platform platform, Map extras, String scheduleFlag) throws APIConnectionException, APIRequestException;
    void cancleSchedule(String name) throws APIConnectionException, APIRequestException;
    void updateSchedule(String name, String time);
}
