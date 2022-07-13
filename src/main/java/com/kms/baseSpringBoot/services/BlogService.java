package com.kms.baseSpringBoot.services;

import java.util.List;

import com.kms.baseSpringBoot.models.Blog;

public interface BlogService {
	List<Blog> getBlogs();
	
	Blog getBlogById(Long id);
	
	Blog createBlog(Blog Blog);

    void deleteBlogById(Long id);

    Blog updateBlog(Blog answer);
}
