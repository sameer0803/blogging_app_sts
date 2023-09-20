package com.codewithsameer.blog.payloads;

public class CommentDto {
	
	private int id;
	private String content;
	private UserDto user;	
	private Integer postDtoID;
	
	
	public CommentDto(int id, String content, UserDto user, Integer postDtoID) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.postDtoID = postDtoID;
	}
	public CommentDto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public Integer getPostDtoID() {
		return postDtoID;
	}
	public void setPostDtoID(Integer postDtoID) {
		this.postDtoID = postDtoID;
	}
	
	
	
}
