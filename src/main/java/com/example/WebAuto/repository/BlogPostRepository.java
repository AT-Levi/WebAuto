package com.example.WebAuto.repository;

import com.example.WebAuto.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {

}
