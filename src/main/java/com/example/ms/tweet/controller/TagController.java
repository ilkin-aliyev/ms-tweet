package com.example.ms.tweet.controller;

import com.example.ms.tweet.model.dto.SaveTagDto;
import com.example.ms.tweet.model.dto.TagDto;
import com.example.ms.tweet.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/tweet/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTag(@RequestBody SaveTagDto dto) {
        tagService.saveTag(dto);
    }

    @GetMapping
    public List<TagDto> getTags() {
        return tagService.getTags();
    }
}
