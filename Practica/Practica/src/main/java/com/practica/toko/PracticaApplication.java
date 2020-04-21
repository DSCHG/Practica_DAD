package com.practica.toko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableCaching
@SpringBootApplication
@EnableHazelcastHttpSession
public class PracticaApplication {

	private static final Log LOG= LogFactory.getLog(PracticaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PracticaApplication.class, args);
	}
	
	@Bean
	public Config config() {
		
		Config config=new Config();
		
		JoinConfig joinConfig=config.getNetworkConfig().getJoin();
		
		joinConfig.getMulticastConfig().setEnabled(false);
		List<String> ips=new ArrayList<String>();
		ips.add("172.18.0.5");
		ips.add("172.18.0.6");
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(ips);
		return config;
		
	}
	
	@Bean
	public CacheManager cacheManager() {
		LOG.info("Activando Cache.......");
		return new ConcurrentMapCacheManager("productos","proveedores");
	}
	

}
