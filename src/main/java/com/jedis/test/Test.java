package com.jedis.test;

import com.jedis.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/1/4
 */
public class Test {

    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        JedisPool jedisPool2 = JedisPoolUtil.getJedisPoolInstance();
        System.out.println(jedisPool==jedisPool2);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            System.out.println(jedis.auth("zhangbiao"));
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JedisPoolUtil.release(jedisPool,jedis);
        }
    }

}
