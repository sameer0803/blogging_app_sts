package com.codewithsameer.blog.services;

import java.util.List;

import com.codewithsameer.blog.payloads.CommentDto;
import com.codewithsameer.blog.payloads.UserDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postID, Integer userID);

	void deleteComment(Integer commentID);

	CommentDto getCommentById(Integer commentID);

	List<CommentDto> getAllComments();

}
