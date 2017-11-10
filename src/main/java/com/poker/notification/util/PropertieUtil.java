package com.poker.notification.util;
import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.util.Properties;

/***
 *
 * 链式配置文件读取工具
 * @author run
 * @create 2017-11-09
 **/
public class PropertieUtil {
    private static Logger logger = Logger.getLogger(PropertieUtil.class.getName());

    private static PropertieUtil instance = new PropertieUtil();

    public static Propertie newBuilder(String fileNamePrefix) {
        return new Propertie(fileNamePrefix);
    }

    /**
     * 配置文件路径
     */

    public static class Propertie {
        private Properties properties;

        private static String confPath = "gameFiles";

        public Propertie(String fileName) {
            properties = new Properties();
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(confPath + "/conf/" + fileName + ".properties");
                properties.load(inputStream);
                inputStream.close();
            } catch (Exception e) {
                logger.error(fileName + " config file not found.");
            }
        }

        public String getProperty(String property) {
            return properties.getProperty(property).trim();
        }
    }
}
