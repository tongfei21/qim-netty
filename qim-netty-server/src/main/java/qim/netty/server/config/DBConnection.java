package qim.netty.server.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DBConnection {

    //利用static（静态）属于类不属于对象，且全局唯一，static属性本身就属于全局唯一
    private static SqlSessionFactory sqlSessionFactory = null;

    private static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            InputStream inputStream = null;
            try {
                InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    //定义返回SqlSession对象的方法
    public static SqlSession openSession(){
        return getSqlSessionFactory().openSession();
    }

    //释放SqlSession对象
    public static void closeSession(SqlSession session){
        if (session != null) {
            session.close();
        }
    }
}
