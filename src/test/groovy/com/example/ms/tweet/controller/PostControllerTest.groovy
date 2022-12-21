package com.example.ms.tweet.controller

import com.example.ms.tweet.exception.ErrorHandler
import com.example.ms.tweet.model.PersonDto
import com.example.ms.tweet.model.dto.SaveCommentDto
import com.example.ms.tweet.service.PersonService
import com.example.ms.tweet.service.PostService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class PostControllerTest extends Specification {
    private PostService postService
    private MockMvc mockMvc

    void setup() {
        postService = Mock()
        def personController = new PostController(postService)
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestAddCommentToPost"() {
        given:
        def id = 1L
        def url = "/v1/tweet/posts/$id/comments"

        def dto = new SaveCommentDto(content: "Mock content")

        def requestBody = '''
                {
                    "content": "Mock content"                     
                }
        '''

        when:
        def result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andReturn()

        then:
        1 * postService.addCommentToPost(id, dto)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }
}
