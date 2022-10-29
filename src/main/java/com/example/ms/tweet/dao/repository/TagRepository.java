package com.example.ms.tweet.dao.repository;

import com.example.ms.tweet.dao.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    List<TagEntity> findByIdIn(Set<Long> ids);
}
