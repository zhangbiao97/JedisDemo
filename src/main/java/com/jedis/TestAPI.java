package com.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2018/12/28
 */
public class TestAPI {

    public static void main(String[] args) {
        Jedis jedis=new Jedis("123.56.9.64",6379);
        System.out.println(jedis.auth("zhangbiao"));
        System.out.println(jedis.ping());

        //mset mget
        /*jedis.mset("k1","v1","k2","v2","k3","v3");
        System.out.println(jedis.mget("k1","k2","k3"));*/

        //list
        /*jedis.lpush("list01","1","2","3","4","5");
        List<String> lists = jedis.lrange("list01", 0, -1);
        for(String str:lists){
            System.out.println(str);
        }*/


        //map
        /*Map<String,String> map=new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","18");
        map.put("gender","男");
        map.put("address","北京市朝阳区双桥");
        String result = jedis.hmset("hash01", map);
        System.out.println(result);
        List<String> lists = jedis.hmget("hash01", "name", "haha", "age", "gender");
        for(String str:lists){
            System.out.println(str);
        }*/

        //set
        /*Long result = jedis.sadd("set01", "1", "2", "3");
        System.out.println(result);*/
        /*Set<String> sets = jedis.smembers("set01");
        for(String str:sets){
            System.out.println(str);
        }*/

        //zset
        /*jedis.zadd("zset01",60,"v1");
        jedis.zadd("zset01",70,"v2");
        jedis.zadd("zset01",80,"v3");
        jedis.zadd("zset01",90,"v4");
        Set<String> zsets = jedis.zrange("zset01", 0, -1);
        for(String str:zsets){
            System.out.println(str);
        }*/
    }

}
