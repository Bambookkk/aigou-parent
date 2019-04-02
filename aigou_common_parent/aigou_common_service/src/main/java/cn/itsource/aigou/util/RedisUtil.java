package cn.itsource.aigou.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Properties;

public class RedisUtil {

    private static JedisPool jedisPool=null;
    //初始化连接池对象
    static {
    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();//连接池配置对象，用来设置一些连接池的参数
        poolConfig.setMaxTotal(20);//最大连接数
        poolConfig.setMaxIdle(5);//最大空闲数
        poolConfig.setMaxWaitMillis(10*1000);//最长等待时间
        poolConfig.setTestOnBorrow(true);//在拿取连接前先测试

        Properties properties = new Properties();
        try {
            properties.load(RedisUtil.class.getClassLoader().getResourceAsStream("redis.properties"));//解决硬编码问题
            jedisPool= new JedisPool(poolConfig,properties.getProperty("redis.host"),
                                                Integer.parseInt(properties.getProperty("redis.port")),
                                                Integer.parseInt(properties.getProperty("redis.timeout")),
                                                properties.getProperty("redis.password"));//获得连接池
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //存入redis
    public static void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis!=null){
                jedis.close();//放回连接池
            }
        }
    }

    //从redis中取出
    public static String get(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }
}
