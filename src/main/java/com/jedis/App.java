package com.jedis;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Jedis jedis=new Jedis("123.56.9.64",6379);
        String authResult=jedis.auth("zhangbiao");
        System.out.println(authResult);
        System.out.println(jedis.ping());
    }
}
