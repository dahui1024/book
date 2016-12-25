package com.midread.book.vo;

import java.util.Date;

public class TxtVo {
	private String id;
	private String title;
	private String desc;
	private Date upload_date;
	private String url;
	private int current_sn;
	private int next_sn = 0;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCurrent_sn() {
		return current_sn;
	}
	public void setCurrent_sn(int current_sn) {
		this.current_sn = current_sn;
	}
	public int getNext_sn() {
		return next_sn;
	}
	public void setNext_sn(int next_sn) {
		this.next_sn = next_sn;
	}
	
}
