package cn.cc1021.jedis.test;

import cn.cc1021.jedis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
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
     * hash 操作
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

    /**
     * 快速入门
     * list 操作
     */
    @Test
    public void test4(){
        //1、获取连接
        Jedis jedis = new Jedis(); //默认值就是 localhost 和 6379

        //2、操作
        //存储 list
        jedis.lpush("mylist", "a", "b", "c");//从左边存
        jedis.rpush("mylist", "a", "b", "c");//从右边存

        //list 获取范围
        List<String> list = jedis.lrange("mylist", 0, -1);
        System.out.println(list);

        //list 弹出
        String element1 = jedis.lpop("mylist");
        System.out.println(element1);

        String element2 = jedis.rpop("mylist");
        System.out.println(element2);

        List<String> list2 = jedis.lrange("mylist", 0, -1);
        System.out.println(list2);

        //3、关闭连接
        jedis.close();
    }

    /**
     * 快速入门
     * set 操作
     */
    @Test
    public void test5(){
        //1、获取连接
        Jedis jedis = new Jedis(); //默认值就是 localhost 和 6379

        //2、操作
        //存储 set
        jedis.sadd("myset", "java", "php", "c++");

        //set 获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);

        //3、关闭连接
        jedis.close();
    }

    /**
     * 快速入门
     * sortedset 操作
     */
    @Test
    public void test6(){
        //1、获取连接
        Jedis jedis = new Jedis(); //默认值就是 localhost 和 6379

        //2、操作
        //存储 sortedset
        jedis.zadd("mysortedset", 3, "亚瑟");
        jedis.zadd("mysortedset", 30, "后羿");
        jedis.zadd("mysortedset", 25, "孙悟空");

        //3、获取 sortedset
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);

        //3、关闭连接
        jedis.close();
    }

    /**
     * jedis 连接池使用
     */
    @Test
    public void test7(){
        //0、创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        //1、创建 Jedis 连接池对象
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);

        //获取连接
        Jedis jedis = jedisPool.getResource();

        //3、使用
        jedis.set("hehe", "heihei");

        //4、关闭 归还到连接池中
        jedis.close();
    }

    /**
     * jedis 连接池工具类使用
     */
    @Test
    public void test8(){
        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();

        //3、使用
        jedis.set("hello", "world");

        //4、关闭 归还到连接池中
        jedis.close();
    }
}
