package com.redis.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

//redis 的java客户端
public class JedisCilent {
	@Test
	public void jedisClient() {
		//需要先在linux上启动redis
		//创建Jedis对象   第一个参数linux远程IP地址192.168.227.130    第二个参数redis为默认端口6379
		Jedis jedis=new Jedis("192.168.227.130",6379);
		//赋值
		jedis.set("s2", "123456");
		//取值
		String result = jedis.get("s2");
		System.out.println(result);
		jedis.close();
	}
	
	@Test
	public void jedisPool() {
		//jedis连接池连接
		JedisPool pool=new JedisPool("192.168.227.130",6379);
		Jedis jedis = pool.getResource();
		jedis.set("s3", "6666");
		String result = jedis.get("s3");
		System.out.println(result);
		
		//关闭
		pool.close();
		jedis.close();
	}
	
	@Test
	public void jedisCluster() {
		
		//jedis连接redis集群
		//创建JedisCluster对象
		//连接redis集群需要先在linux防火墙上开放以下端口
		Set<HostAndPort> nodes=new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.227.130", 7001));
		nodes.add(new HostAndPort("192.168.227.130", 7002));
		nodes.add(new HostAndPort("192.168.227.130", 7003));
		nodes.add(new HostAndPort("192.168.227.130", 7004));
		nodes.add(new HostAndPort("192.168.227.130", 7005));
		nodes.add(new HostAndPort("192.168.227.130", 7006));
		
		JedisCluster jedis=new JedisCluster(nodes);
		jedis.set("s1", "999");
		String result = jedis.get("s1");
		System.out.println(result);
		jedis.close();
	}
}
