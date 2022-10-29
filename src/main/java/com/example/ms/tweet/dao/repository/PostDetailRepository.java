package com.example.ms.tweet.dao.repository;

import com.example.ms.tweet.dao.entity.PostDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailRepository extends JpaRepository<PostDetailEntity, Long> {
}
