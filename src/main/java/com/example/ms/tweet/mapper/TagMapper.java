package com.example.ms.tweet.mapper;

import com.example.ms.tweet.dao.entity.TagEntity;
import com.example.ms.tweet.model.dto.SaveTagDto;
import com.example.ms.tweet.model.dto.TagDto;

public class TagMapper {
    public static TagEntity mapSaveTagRequestToEntity(SaveTagDto dto) {
        return TagEntity.builder()
                .name(dto.getName())
                .build();
    }

    public static TagDto mapEntityToDto(TagEntity entity) {
        return TagDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
