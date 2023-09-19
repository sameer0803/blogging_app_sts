package com.codewithsameer.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithsameer.blog.payloads.APIResponse;
import com.codewithsameer.blog.payloads.CategoryDto;
import com.codewithsameer.blog.payloads.CommentDto;
import com.codewithsameer.blog.payloads.PostDto;
import com.codewithsameer.blog.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")

public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("create/userId/{userId}/post/{postID}")
	public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto createDto,
			@PathVariable("postID") Integer postId, @PathVariable("userId") Integer userId) {
		CommentDto createComment = this.commentService.createComment(createDto, postId, userId);
		return new ResponseEntity<>(createComment, HttpStatus.CREATED);
	}
	

	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<APIResponse> deleteComments(@PathVariable("commentId") Integer commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<APIResponse>(new APIResponse("Comment deleted successfully!!", true), HttpStatus.OK);
	}
	
	@GetMapping("/get/{commentId}")
	public ResponseEntity<CommentDto> getComments(@PathVariable("commentId") Integer commentId) {
		CommentDto comment = this.commentService.getCommentById(commentId);
		return new ResponseEntity<CommentDto>(comment, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CommentDto>> getAllComments() {
		 List<CommentDto> allComments = this.commentService.getAllComments();
		return new ResponseEntity<List<CommentDto>>(allComments, HttpStatus.OK);
	}

}
