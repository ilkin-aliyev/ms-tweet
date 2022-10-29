package com.example.ms.tweet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavePostDto {
    private String title;
    private String createdBy;
    private Set<Long> tagIds;
}
