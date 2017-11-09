package com.poker.notification.push;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;

import java.util.List;

/**
 * 操作连接设备接口
 *
 * @author run
 * @create 2017-11-09
 **/
public interface DeviceService {
    void setTags(String registerId, List<String> tags) throws APIConnectionException, APIRequestException;
    List<String> getTags(String registerId) throws APIConnectionException, APIRequestException;
    void removeTags(List<String> tags) throws APIConnectionException, APIRequestException;
    List<String> getTagList() throws APIConnectionException, APIRequestException;
    void setAlias(String registerId);
}
