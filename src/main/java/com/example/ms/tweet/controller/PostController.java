package com.example.ms.tweet.controller;

import com.example.ms.tweet.model.dto.PostDto;
import com.example.ms.tweet.model.dto.SaveCommentDto;
import com.example.ms.tweet.model.dto.SavePostDto;
import com.example.ms.tweet.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/tweet/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@permissionService.checkPermission(#userId, 'UPDATE', 'TWEET')")
    public void savePost(@RequestParam String userId,
                         @RequestBody SavePostDto dto) {
        System.out.println("Already success");
//        postService.savePost(dto);
    }

    @PatchMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCommentToPost(@PathVariable Long id,
                                 @RequestBody SaveCommentDto dto) {
        postService.addCommentToPost(id, dto);
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }
}
