package com.charge.web.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by vincent on 19/09/2018.
 */
public class RedisPoolUtil {

    private static JedisPool jedisPool = null;
    private static String redisConfigFile = "redis.properties";
    //把redis连接对象放到本地线程中
    private static ThreadLocal<Jedis> local = new ThreadLocal<Jedis>();

    //不允许通过new创建该类的实例
    private RedisPoolUtil() {
    }

    /**
     * 初始化Redis连接池
     */
    static void initialPool() {
        try {
            Properties props = new Properties();
            //加载连接池配置文件
            props.load(RedisPoolUtil.class.getClassLoader().getResourceAsStream(redisConfigFile));
            // 创建jedis池配置实例
            JedisPoolConfig config = new JedisPoolConfig();
            // 设置池配置项值
            config.setMaxTotal(Integer.valueOf(props.getProperty("jedis.pool.maxActive")));
            config.setMaxIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")));
            config.setMaxWaitMillis(Long.valueOf(props.getProperty("jedis.pool.maxWait")));
            config.setTestOnBorrow(Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));
            // 根据配置实例化jedis池
            jedisPool = new JedisPool(config, props.getProperty("redis.ip"),
                    Integer.valueOf(props.getProperty("redis.port")),
                    Integer.valueOf(props.getProperty("redis.timeout")),
                    props.getProperty("redis.passWord"));
            //System.out.println("线程池被成功初始化");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得连接
     *
     * @return Jedis
     */
    public static Jedis getConn() {
        //Redis对象
        Jedis jedis = local.get();
        if (jedis == null) {
            if (jedisPool == null) {
                initialPool();
            }
            jedis = jedisPool.getResource();
            local.set(jedis);
        }
        return jedis;
    }

    //新版本用close归还连接
    public static void closeConn() {
        //从本地线程中获取
        Jedis jedis = local.get();
        if (jedis != null) {
            jedis.close();
        }
        local.set(null);
    }

    //关闭池
    public static void closePool() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    public static void storeRedis(String key, String value) {
        Jedis jedis = getConn();
        jedis.set(key, value);
        closeConn();
    }

    public static void storeRedis(Map<String, String> map) {
        Jedis jedis = getConn();
        map.forEach((key, value) -> jedis.set(key, value));
        closeConn();
    }

    public static String getRedis(String key) {
        Jedis jedis = getConn();
        String value = jedis.get(key);
        closeConn();
        return value;
    }

    public static Map<String, String> getRedis(String... keys) {
        Jedis jedis = getConn();
        Map<String, String> mapGet = new HashMap<>();
        for (String key : keys) {
            mapGet.put(key, jedis.get(key));
        }
        closeConn();
        return mapGet;
    }
}

