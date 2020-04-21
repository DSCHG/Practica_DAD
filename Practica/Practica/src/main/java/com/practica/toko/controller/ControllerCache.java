package com.practica.toko.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerCache {

	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping("/productos")
	public String getCacheProductos(Model model){
		ConcurrentMapCacheManager cacheMan=(ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache=(ConcurrentMapCache) cacheMan.getCache("productos");
		model.addAttribute("cache", cache.getNativeCache().toString());
		return "cache";
	}
	@RequestMapping(value="/proveedores",method=RequestMethod.GET)
	public String getCacheProveedores(Model model){
		ConcurrentMapCacheManager cacheMan=(ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache=(ConcurrentMapCache) cacheMan.getCache("proveedores");
		model.addAttribute("cache", cache.getNativeCache().toString());
		return "cache";
	}
	
}
