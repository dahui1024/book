package com.midread.book.redis;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class StringTemplate extends AbstractTemplate<String> {
	protected static Gson gson = new Gson();
	public StringTemplate() {
		super(String.class);
	}
	/**
	 * Redis
	 * 
	 * RedisTemplate<String, String>
	 * 
	 * get value
	 * 
	 * */
	public <T> T getObject(String key, Class<T> t){
		String json = opsForValue().get(key);
		return gson.fromJson(json, t);
	}
	/**
	 * Redis
	 * 
	 * RedisTemplate<String, String>
	 * 
	 * set value
	 * 
	 * */
	public void setObject(String key, Object object, long expire_time){
		String json = gson.toJson(object);
		
		opsForValue().set(key,json);
		if(expire_time > 0){
			expire(key, expire_time, TimeUnit.SECONDS);
		}
	}
	/**
	 * Redis
	 * 
	 * RedisTemplate<String, String>
	 * 
	 * set list
	 * 
	 * */
	public void leftPush(String key, Object object) {
		if(object == null){
			return;
		}
		opsForList().leftPush(key, gson.toJson(object));
	}
	public void rightPush(String key, Object object) {
		if(object == null){
			return;
		}
		opsForList().rightPush(key, gson.toJson(object));
	}
	public void setList(String key, List<? extends Object> list, long expire_time) {
		if(list.isEmpty()){
			return;
		}
		List<String> jsonList = new LinkedList<String>();
		for(Object object : list){
			jsonList.add(gson.toJson(object));
		}
		
		delete(key);
		opsForList().rightPushAll(key, jsonList);
		if(expire_time > 0){
			expire(key, expire_time, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Redis
	 * 
	 * RedisTemplate<String, String>
	 * 
	 * get list
	 * 
	 * */
	public <T>List<T> getList(String key, Class<T> c) {
		return getList(key, c, 0, Integer.MAX_VALUE	);
	}
	public <T>List<T> getList(String key, Class<T> c, int offset, int limit) {
		if(!hasKey(key)){
			return null;
		}
		int cursor = offset+limit > 1 ? offset + limit - 1 : Integer.MAX_VALUE;
		List<String> jsonList = opsForList().range(key, offset, cursor);
		List<T> list = new LinkedList<T>();
		for(String json : jsonList){
			list.add(gson.fromJson(json, c));
		}
		return list;
	}
	
	public Long getExpire(String key){
		if(!hasKey(key)){
			return -1l;
		}
		return getExpire(key, TimeUnit.SECONDS);
	}
	
}
