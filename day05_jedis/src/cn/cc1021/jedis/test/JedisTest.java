package cn.cc1021.jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * jedis 测试类
 */
public class JedisTest {

    /**
     * 快速入门
     */
    @Test
    public void test1(){
        //1、获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        //2、操作
        jedis.set("username", "chengcheng");

        //3、关闭连接
        jedis.close();
    }

    /**
     * 快速入门
     * 字符串
     */
    @Test
    public void test2(){
        //1、获取连接
        Jedis jedis = new Jedis();//默认值就是 localhost 和 6379

        //2、操作
        //存储字符串
        jedis.set("username", "chengcheng");
        //获取
        String username = jedis.get("username");
        System.out.println(username);

        //可以使用 setex() 方法存储可以指定过期时间的 key value
        jedis.setex("activecode", 20, "hehe"); //将 activecode:hehe 存入redis，并且20秒后自动删除

        //3、关闭连接
        jedis.close();
    }

    /**
     * 快速入门
     * 字符串
     */
    @Test
    public void test3(){
        //1、获取连接
        Jedis jedis = new Jedis(); //默认值就是 localhost 和 6379

        //2、操作
        //存储hash
        jedis.hset("user", "name", "lisi");
        jedis.hset("user", "age", "23");
        jedis.hset("user", "gender", "male");

        //获取hash
        String name = jedis.hget("user", "name");
        System.out.println(name);

        //获取hash的所有map中的数据
        Map<String, String> user = jedis.hgetAll("user");

        //keyset
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            //获取 value
            String value = user.get(key);
            System.out.println(key + ":" + value);
        }

        //3、关闭连接
        jedis.close();
    }
}
