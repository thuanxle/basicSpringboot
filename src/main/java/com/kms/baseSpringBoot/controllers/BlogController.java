package com.kms.baseSpringBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kms.baseSpringBoot.models.Blog;
import com.kms.baseSpringBoot.payload.response.MessageResponse;
import com.kms.baseSpringBoot.services.BlogService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Blog> getAll() {
		return blogService.getBlogs();
	}
	
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity create(@RequestBody Blog blog) {
		blogService.createBlog(blog);
		return new ResponseEntity<>(new MessageResponse("Create success"), HttpStatus.OK);
	}
	
	@PutMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity update(@RequestBody Blog blog) {
		blogService.updateBlog(blog);
		return new ResponseEntity<>(new MessageResponse("Update success"), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteTopicById(@PathVariable Long id) {
		blogService.deleteBlogById(id);
        return new ResponseEntity<>(new MessageResponse("Delete succeed!"), HttpStatus.OK);
    }
}
