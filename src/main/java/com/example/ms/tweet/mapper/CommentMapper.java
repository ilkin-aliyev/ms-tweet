package com.example.ms.tweet.mapper;

import com.example.ms.tweet.dao.entity.CommentEntity;
import com.example.ms.tweet.model.dto.CommentDto;

public class CommentMapper {
    public static CommentDto mapEntityToDto(CommentEntity entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .viewed(entity.isViewed())
                .build();
    }
}
