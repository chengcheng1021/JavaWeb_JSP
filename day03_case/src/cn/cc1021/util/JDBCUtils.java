package cn.cc1021.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *  JDBC工具类 使用 Druid 连接池
 */
public class JDBCUtils {

    private static DataSource ds;

    static {
        try {
            //1、加载配置文件
            Properties pro = new Properties();
            //使用 ClassLoader 加载配置文件，获取字节输入流
            InputStream is = cn.cc1021.util.JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);

            //2、初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 获取连接 Connection 对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
