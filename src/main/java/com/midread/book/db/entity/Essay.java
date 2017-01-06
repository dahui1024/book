package com.midread.book.db.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.midread.book.utils.CommonConstant;

@Entity(value = "essay", noClassnameStored = true)
public class Essay {

	@Id
	private ObjectId id;
	private String name;
	private String description;
	private String content;
	private String password;
	private String oss_url;
	private Date upload_time;
	private CommonConstant.STATUS status;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOss_url() {
		return oss_url;
	}
	public void setOss_url(String oss_url) {
		this.oss_url = oss_url;
	}
	public Date getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}
	public CommonConstant.STATUS getStatus() {
		return status;
	}
	public void setStatus(CommonConstant.STATUS status) {
		this.status = status;
	}

	

}
