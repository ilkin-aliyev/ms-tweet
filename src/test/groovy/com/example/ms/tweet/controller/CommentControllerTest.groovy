package com.example.ms.tweet.controller

import com.example.ms.tweet.exception.ErrorHandler
import com.example.ms.tweet.model.dto.SaveCommentDto
import com.example.ms.tweet.service.CommentService
import com.example.ms.tweet.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class CommentControllerTest extends Specification {
    private CommentService commentService
    private MockMvc mockMvc

    void setup() {
        commentService = Mock()
        def commentController = new CommentController(commentService)
        mockMvc = MockMvcBuilders.standaloneSetup(commentController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestChangeCommentContent"() {
        given:
        def id = 1L
        def url = "/v1/tweet/comments/$id/content"
        def content = "Mock"

        when:
        def result = mockMvc.perform(patch(url)
                .param("value", content)
        ).andReturn()

        then:
        1 * commentService.changeCommentContent(id, content)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }

 /*
 given:
 def id = 1
 def entity = EnhancedRandom.random(CourseEntity)

 def dto = CourseMapper.mapEntityToDto(entity)

 when:
    def result = courseService.getCourse(id)

 then:

1 * courseRepository.findById(id) >> Optional.of(entity)
 result == dto

  */

}
