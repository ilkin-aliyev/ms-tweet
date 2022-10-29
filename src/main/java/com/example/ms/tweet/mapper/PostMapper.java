package com.example.ms.tweet.mapper;

import com.example.ms.tweet.dao.entity.PostEntity;
import com.example.ms.tweet.model.dto.PostDto;

public class PostMapper {
    public static PostDto mapEntityToDto(PostEntity entity) {
        return new PostDto(entity.getId(), entity.getTitle());
    }
}
