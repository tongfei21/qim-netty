package qim.netty.server.util;

import java.util.Random;
import java.util.UUID;

public class TokenUtil {

    /**
     *  根据配置的tokenStyle生成不同风格的token
     */
    public static String TokenUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 简单uuid (不带下划线)
     * @param uidtype  boolean 不带下划线，
     *        false 带下划线
     */
    public static String TokenUUID(boolean uidtype){
        if (uidtype){
            return UUID.randomUUID().toString().replaceAll("-", "");
        }else{
            return UUID.randomUUID().toString();
        }

    }

    /**
     * 64位随机字符串
     * @return String
     */
    public static String TokenRandom64(){
        return getRandomString(64);
    }

    /**
     * 64位随机字符串
     * @return String
     */
    public static String TokenRandom(int len){
        return getRandomString(len);
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param length 字符串的长度
     * @return 时间戳+一个随机字符串
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String time = String.valueOf(System.currentTimeMillis()); //13位时间戳
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        sb.append(time);
        for (int i = 0; i < (length-13); i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
