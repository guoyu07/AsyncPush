package com.poker.notification.push.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.poker.notification.push.PushService;
import com.poker.notification.util.JPushUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 极光推送
 *
 * @author run
 * @create 2017-11-08
 **/
public class JPushService implements PushService {

    @Override
    public void sendWithTag(String content, Platform platform, List<String> tags, Map extras, String scheduleTag) throws APIConnectionException, APIRequestException {
        PushPayload payload = null;
        if (extras != null) {
            payload = PushPayload.newBuilder()
                    .setPlatform(platform)
                    .setAudience(Audience.tag_and(tags))
                    .setNotification(Notification.newBuilder()
                            .setAlert(content)
                            .addPlatformNotification(AndroidNotification.newBuilder()
                                    .addExtras(extras).build())
                            .addPlatformNotification(IosNotification.newBuilder()
                                    .incrBadge(1)
                                    .addExtras(extras).build())
                            .build())
                    .build();
        } else {
            payload = PushPayload.newBuilder()
                    .setPlatform(platform)
                    .setAudience(Audience.tag_and(tags))
                    .setNotification(Notification.newBuilder()
                            .setAlert(content)
                            .addPlatformNotification(AndroidNotification.newBuilder()
                                    .build())
                            .addPlatformNotification(IosNotification.newBuilder()
                                    .incrBadge(1)
                                    .build())
                            .build())
                    .build();
        }

        if (scheduleTag != null && !"".equals(scheduleTag)) {
            String scheduleName = scheduleTag;
            String[] str = scheduleTag.split("\\_");
            Long scheduleTime = Long.parseLong(str[1]);
            Date date = new Date(scheduleTime * 1000);
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            JPushUtil.getInstance().getJPushClient().createSingleSchedule(scheduleName, formater.format(date), payload);
        } else {
            JPushUtil.getInstance().getJPushClient().sendPush(payload);
        }
    }

    @Override
    public void send(String content, Platform platform, Map extras, String scheduleTag) throws APIConnectionException, APIRequestException {
        PushPayload payload = null;
        if (extras != null) {
            payload = PushPayload.newBuilder()
                    .setPlatform(platform)
                    .setAudience(Audience.all())
                    .setNotification(Notification.newBuilder()
                            .setAlert(content)
                            .addPlatformNotification(AndroidNotification.newBuilder()
                                    .addExtras(extras).build())
                            .addPlatformNotification(IosNotification.newBuilder()
                                    .incrBadge(1)
                                    .addExtras(extras).build())
                            .build())
                    .build();
        } else {
            payload = PushPayload.newBuilder()
                    .setPlatform(platform)
                    .setAudience(Audience.all())
                    .setNotification(Notification.newBuilder()
                            .setAlert(content)
                            .addPlatformNotification(AndroidNotification.newBuilder()
                                    .addExtras(extras).build())
                            .addPlatformNotification(IosNotification.newBuilder()
                                    .incrBadge(1)
                                    .addExtras(extras).build())
                            .build())
                    .build();
        }

        if (scheduleTag != null && !"".equals(scheduleTag)) {
            String scheduleName = scheduleTag;
            String[] str = scheduleTag.split("\\_");
            Long scheduleTime = Long.parseLong(str[1]);
            Date date = new Date(scheduleTime * 1000);
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            JPushUtil.getInstance().getJPushClient().createSingleSchedule(scheduleName, formater.format(date), payload);
        } else {
            JPushUtil.getInstance().getJPushClient().sendPush(payload);
        }
    }

    @Override
    public void cancleSchedule(String name) throws APIConnectionException, APIRequestException {
        JPushUtil.getInstance().getJPushClient().deleteSchedule(name);
    }

    @Override
    public void updateSchedule(String name, String time) {

    }
}
