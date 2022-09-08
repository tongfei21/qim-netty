package qim.netty.server.processor;


public class UserProcessor {

//    public static void main(String[] args) {
//        SqlSession session = null;
//        session = DBConnection.openSession();
//        //batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
//
//        // 不用接口的方式
//        UserMapper userMapper = session.getMapper(UserMapper.class);
//
//        //User user = userMapper.selectByPrimaryKey(1000);
//        User user = new User();
//        user.setId(1001);
//        user.setAddtime(DateUtil.getDate());
//        user.setIntro("这是一条测试数据");
//        user.setName("小王");
//        String pwd = SecurityUtil.encryptPassword("1234562");
//        System.out.println("passwd:"+pwd);
//        user.setPasswd(pwd);
//
//        userMapper.insert(user);
//        session.commit();
//        DBConnection.closeSession(session);
//
//        System.out.println("------------------------:"+ TokenUtil.TokenUUID(true));
//        System.out.println("------------------------:"+ TokenUtil.TokenUUID());
//
//    }
}
