package com.poker.notification.push;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import com.poker.notification.config.PushAction;
import com.poker.notification.push.jpush.JPushDeviceService;
import com.poker.notification.push.jpush.JPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 异步推送任务
 *
 * @author run
 * @create 2017-11-08
 **/
public class PushPool implements Runnable {

    protected static final Logger LOG = LoggerFactory.getLogger(PushPool.class);

    private Map<String, Object> pushContent;

    public PushPool(Map pushContent) {
        this.pushContent = pushContent;
    }

    @Override
    public void run() {
        try {
            PushAction action = (PushAction) pushContent.get("action");
            if (action == PushAction.SEND) {
                PushService jPushService = new JPushService();
                jPushService.send((String) pushContent.get("content"), (cn.jpush.api.push.model.Platform) pushContent.get("platform"), (Map) pushContent.get("extra"), (String) pushContent.get("scheduleFlag"));
            } else if (action == PushAction.SEND_WITH_TAG) {
                PushService jPushService = new JPushService();
                jPushService.sendWithTag((String) pushContent.get("content"), (cn.jpush.api.push.model.Platform) pushContent.get("platform"), (List) pushContent.get("tags"), (Map) pushContent.get("extra"), (String) pushContent.get("scheduleFlag"));
            } else if (action == PushAction.ADD_TAG) {
                JPushDeviceService jPushService = new JPushDeviceService();
                jPushService.setTags((String) pushContent.get("registerId"), (List<String>) pushContent.get("tags"));
            } else if (action == PushAction.REMOVE_TAG) {
                DeviceService deviceService = new JPushDeviceService();
                deviceService.removeTags((List) pushContent.get("tags"));
            }

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage());
        }
    }
}

