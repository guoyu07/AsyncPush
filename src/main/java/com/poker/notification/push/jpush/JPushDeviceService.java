package com.poker.notification.push.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import com.poker.notification.push.DeviceService;
import com.poker.notification.util.JPushUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 设备别名/标签等操作
 *
 * @author run
 * @create 2017-11-09
 **/
public class JPushDeviceService implements DeviceService {
    @Override
    public void setTags(String registerId, List<String> tags) throws APIConnectionException, APIRequestException {
        Set<String> userSet = new HashSet<>();
        userSet.add(registerId);
        if (tags != null) {
            for (String tag : tags) {
                JPushUtil.getInstance().getJPushClient().addRemoveDevicesFromTag(tag, userSet, null);
            }
        }

    }

    @Override
    public List<String> getTags(String registerId) throws APIConnectionException, APIRequestException {
        return JPushUtil.getInstance().getJPushClient().getDeviceTagAlias(registerId).tags;
    }

    @Override
    public void removeTags(List<String> tags) throws APIConnectionException, APIRequestException {
        for (String tag : tags) {
            JPushUtil.getInstance().getJPushClient().deleteTag(tag, Platform.all().toString());
        }
    }

    @Override
    public List<String> getTagList() throws APIConnectionException, APIRequestException {
        return JPushUtil.getInstance().getJPushClient().getTagList().tags;
    }

    @Override
    public void setAlias(String registerId) {
    }
}
