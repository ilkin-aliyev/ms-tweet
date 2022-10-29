package com.example.ms.tweet.mapper.factory;

import com.example.ms.tweet.dao.entity.PostEntity;
import com.example.ms.tweet.model.dto.SavePostDto;

public class PostFactory {
    public static PostEntity buildPostEntity(SavePostDto dto) {
        return PostEntity.builder()
                .title(dto.getTitle())
                .build();
    }
}
