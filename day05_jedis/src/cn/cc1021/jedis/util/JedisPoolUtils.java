package cn.cc1021.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Properties;

/**
 * JedisPool 工具类
 *  加载p配置文件，配置连接池的参数
 *  提供获取连接池的方法
 */
public class JedisPoolUtils {

    private static JedisPool jedisPool;

    static {
        //读取配置文件
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建 Properties 对象
        Properties pro = new Properties();
        //关联文件
        try {
            pro.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取数据，配置到 JedisPoolConfig 中
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        //初始化 JedisPool
        jedisPool = new JedisPool(pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
    }

    /**
     * 获取连接的方法
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
