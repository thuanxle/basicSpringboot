package com.kms.baseSpringBoot.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.baseSpringBoot.models.Blog;
import com.kms.baseSpringBoot.repository.BlogRepository;
import com.kms.baseSpringBoot.services.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	@Override
	public List<Blog> getBlogs() {
		return blogRepository.findAll();
	}

	@Override
	public Blog getBlogById(Long id) {
		return blogRepository.getById(id);
	}

	@Override
	public Blog createBlog(Blog blog) {
		return blogRepository.save(blog);
	}

	@Override
	public void deleteBlogById(Long id) {
		blogRepository.deleteById(id);
	}

	@Override
	public Blog updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogRepository.save(blog);
	}
	
}
