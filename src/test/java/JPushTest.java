import cn.jpush.api.push.model.Platform;
import com.poker.notification.config.PushAction;
import com.poker.notification.push.SendNotification;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试方法
 *
 * @author run
 * @create 2017-11-09
 * 
 **/
public class JPushTest {

    @Test
    public void testSendSchedule() {
        Map<String, Object> sendMap = new HashMap();
        sendMap.put("action", PushAction.SEND_WITH_TAG);
        List<String> tags = new ArrayList<String>();
        tags.add("123xzs");
        sendMap.put("tags",tags);
        sendMap.put("platfom", Platform.all());
        sendMap.put("scheduleTag", "123xzs_1510217887");
        sendMap.put("content",String.format("您报名的比赛%s将于%s开始比赛，点击前往>>"));
        SendNotification.getInstance().send(sendMap);
    }

    @Test
    public void testSendAdd() {
        int raceId = 1;
        Map<String, Object> sendMap = new HashMap();
        sendMap.put("action", PushAction.ADD_TAG);
        List<String> tags = new ArrayList<String>();
        tags.add("123xzs");
        sendMap.put("tags",tags);
        sendMap.put("platfom", Platform.all());
        SendNotification.getInstance().send(sendMap);
    }

}
