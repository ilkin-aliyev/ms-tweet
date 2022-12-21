package com.example.ms.tweet.controller

import com.example.ms.tweet.exception.ErrorHandler
import com.example.ms.tweet.model.PersonDto
import com.example.ms.tweet.service.PersonService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class PersonControllerTest extends Specification {

    private PersonService personService
    private MockMvc mockMvc

    void setup() {
        personService = Mock()
        def personController = new PersonController(personService)
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestGetPersonById"() {
        given:
        def id = 1L
        def url = "/v1/person/$id"

        def dto = new PersonDto(name: "Ilkin")

        def responseView = dto

        def expectedResponse = '''
                {
                    "name": "Ilkin"                     
                }
        '''

        when:
        def result = mockMvc.perform(get(url)).andReturn()

        then:
        1 * personService.getPerson(id) >> responseView

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }
}
