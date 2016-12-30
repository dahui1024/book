package com.midread.book.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoClientFactoryBean extends AbstractFactoryBean<MongoClient> {
	 
	private String userName;
	private String password;
	private String dataBaseName;
	private String host;
	private int port;
	private int connectTimeout;

    @Override
    public Class<?> getObjectType() {
        return MongoClient.class;
    }

    @Override
    protected MongoClient createInstance() throws Exception {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectTimeout(connectTimeout).build();

		MongoCredential credential = MongoCredential.createCredential(userName, dataBaseName, password.toCharArray());
		List<MongoCredential> list = new ArrayList<MongoCredential>();
		list.add(credential);

		MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), list, clientOptions);
        
        return mongoClient;
    }

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


}
