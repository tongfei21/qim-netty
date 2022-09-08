package qim.netty.server.processor;

import org.apache.ibatis.session.SqlSession;
import qim.netty.server.config.DBConnection;
import qim.netty.server.mapper.WtsUserMapper;
import qim.netty.server.model.WtsUser;
import qim.netty.server.redis.Redis;
import qim.netty.server.util.TokenUtil;

public class LoginProcessor {

    public static void test(){
        System.out.println("where is netty~  test all!");
    }

    public static void test1(){
        System.out.println("win from test2!");
    }
    /**
     * 登录
     * @param name
     * @param pwd
     */
    public void login(String name,String pwd,byte type){
        SqlSession session = null;
        session = DBConnection.openSession();
        //batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

        // 不用接口的方式
        WtsUserMapper userMapper = session.getMapper(WtsUserMapper.class);
        WtsUser user = new WtsUser();
        user.setUsername(name);
        user.setPassword(pwd);
        user.setType(type);

        user = userMapper.selectBynameandPwd(user);
        String time = String.valueOf(System.currentTimeMillis());
        if (user != null){

            //生成token
            try {
                Redis redis = Redis.redisInstance();
                String token = TokenUtil.TokenRandom(20);
                redis.set("test1",token,60*2);
                redis = null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("test is empty");
        }
        DBConnection.closeSession(session);
    }

}
