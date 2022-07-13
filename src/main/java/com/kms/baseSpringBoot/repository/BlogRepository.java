package com.kms.baseSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kms.baseSpringBoot.models.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
