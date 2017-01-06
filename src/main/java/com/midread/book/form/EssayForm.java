package com.midread.book.form;

import java.io.Serializable;

public class EssayForm implements Serializable {
	private static final long serialVersionUID = -2457547290959703993L;
	private String name;
	private String description;
	private String content;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
