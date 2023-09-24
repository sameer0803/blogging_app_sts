package com.codewithsameer.blog.services;

import java.util.List;

import com.codewithsameer.blog.entities.Comment;
import com.codewithsameer.blog.payloads.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto commentDto, Integer postID, Integer userId) ;
	public void deleteComment(Integer commentID);
	public CommentDto getCommentById(Integer commentID) ;
	public List<CommentDto> getAllComments() ;
	public CommentDto EntityToDto(Comment comment) ;


}
