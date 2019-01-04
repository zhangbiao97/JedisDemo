package com.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 〈JedisPool单例模式〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/1/4
 */
public class JedisPoolUtil {

    private static volatile JedisPool jedisPool=null;

    private JedisPoolUtil(){}

    public static JedisPool getJedisPoolInstance(){
        if(jedisPool==null){
            synchronized (JedisPoolUtil.class){
                if(jedisPool==null){
                    JedisPoolConfig config=new JedisPoolConfig();
                    config.setMaxIdle(32);
                    config.setMaxWaitMillis(100*1000);
                    config.setMaxTotal(100);
                    jedisPool=new JedisPool(config,"123.56.9.64",6379);
                }
            }
        }
        return jedisPool;
    }

    /**
     * 释放资源，把jedis重新放入池中
     * @param jedisPool
     * @param jedis
     */
    public static void  release(JedisPool jedisPool, Jedis jedis){
        if(jedis!=null){
            jedisPool.returnBrokenResource(jedis);
        }
    }

}
