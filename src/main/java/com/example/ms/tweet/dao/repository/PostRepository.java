package com.example.ms.tweet.dao.repository;

import com.example.ms.tweet.dao.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
