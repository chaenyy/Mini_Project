package com.kh.chm.miniproject.member;

import java.io.Serializable;
import java.util.Objects;

public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pw;
	private String name;
	
	public Member() {
		super();
	}

	public Member(String id, String pw, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public Member(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id:"+id+", pw:"+pw+", name:" + name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Member other = (Member)obj;
		return Objects.equals(id, other.id);
	}
}
