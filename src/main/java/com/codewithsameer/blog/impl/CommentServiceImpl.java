package com.codewithsameer.blog.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithsameer.blog.entities.CategoriesEntity;
import com.codewithsameer.blog.entities.Comment;
import com.codewithsameer.blog.entities.Post;
import com.codewithsameer.blog.entities.UserEntity;
import com.codewithsameer.blog.exceptions.ResourceNotFoundException;
import com.codewithsameer.blog.payloads.CategoryDto;
import com.codewithsameer.blog.payloads.CommentDto;
import com.codewithsameer.blog.payloads.PostDto;
import com.codewithsameer.blog.payloads.UserDto;
import com.codewithsameer.blog.repositories.CommentRepo;
import com.codewithsameer.blog.repositories.PostRepo;
import com.codewithsameer.blog.repositories.UserRepo;
import com.codewithsameer.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postID, Integer userId) {

		Post post = this.postRepo.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("POst ID", "PostId", postID));

		UserEntity user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Id", "user Id", userId));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUsers(user);
		Comment savedComment = commentRepo.save(comment);

		CommentDto commentDTO = this.modelMapper.map(savedComment, CommentDto.class);
		commentDTO.setUser(this.modelMapper.map(user, UserDto.class));
		commentDTO.setPostDtoID(post.getPostId());
		System.out.println(commentDTO);
		return commentDTO;
	}

	@Override
	public void deleteComment(Integer commentID) {

		Comment comment = this.commentRepo.findById(commentID)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentID", commentID));
		this.commentRepo.delete(comment);
	}

	@Override
	public CommentDto getCommentById(Integer commentID) {
		Comment comment = this.commentRepo.findById(commentID)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentID", commentID));

		System.out.println("Comments------->" + comment);

		UserEntity users = comment.getUsers();

		UserDto userDto = this.modelMapper.map(users, UserDto.class);

		CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);

		Integer postId = comment.getPost().getPostId();

		commentDto.setPostDtoID(postId);

		commentDto.setUser(userDto);
		System.out.println("USERS" + commentDto.getUser());

		return commentDto;
	}

	@Override
	public List<CommentDto> getAllComments() {

		List<Comment> allCommentList = this.commentRepo.findAll();

		List<CommentDto> DtoList = allCommentList.stream().map((content) -> this.EntityToDto(content))
				.collect(Collectors.toList());

		for (Comment commentEntity : allCommentList) {

			for (CommentDto commentDTO : DtoList) {

				if (commentEntity.getId() == commentDTO.getId()) {

					commentDTO.setUser(this.modelMapper.map(commentEntity.getUsers(), UserDto.class));
					System.out.println("" + commentDTO.getUser());

					commentDTO.setPostDtoID((this.modelMapper.map(commentEntity.getPost(), PostDto.class)).getId());

				}
			}

		}

		return DtoList;
	}

	private Comment dtoToEntity(CommentDto commentDto) {
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		return comment;
	}

	public CommentDto EntityToDto(Comment comment) {
		CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);
		return commentDto;
	}
}
