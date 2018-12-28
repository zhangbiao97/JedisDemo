package com.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 〈Jedis事务〉<br>
 * 〈
 *      模拟一张信用卡消费的情况：
 *          balance:100元，debt:0
 *          通过watch来监控balance,
 *          每次消费10元，那么balance-10=90,debt=10,
 *          如果balance小于5元，那么回滚,
 *          通过事务来完成。
 *
 *  〉
 *
 * @author Administrator
 * @create 2018/12/28
 */
public class TransactionTest {

    public static void main(String[] args) {
        TransactionTest transactionTest=new TransactionTest();
        try {
            transactionTest.transTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void transTest() throws InterruptedException {
        //连接到redis
        Jedis jedis=new Jedis("主机IP",6379);
        //redis密码（如果配置文件中没有添加安全认证，可以忽略）
        System.out.println(jedis.auth("********"));
        //检查是否连接成功 返回PONG代表成功  其他失败
        System.out.println(jedis.ping());
        int money=5;
        //消费
        int consume=10;
        //获取balance转成int类型
        int balance = Integer.parseInt(jedis.get("balance"));
        jedis.watch("balance");
        Thread.sleep(10000);
        if(balance<=money){
            //取消key的监控
            jedis.unwatch();
            System.out.println("modifiy");
        }else{
            //事务 每次执行get set 等一系列操作都添加到队列中
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance",consume);
            transaction.incrBy("debt",consume);
            //把队列中的命令一并执行
            transaction.exec();
            System.out.println("balance:"+jedis.get("balance"));
            System.out.println("debt:"+jedis.get("debt"));
        }
    }

}
