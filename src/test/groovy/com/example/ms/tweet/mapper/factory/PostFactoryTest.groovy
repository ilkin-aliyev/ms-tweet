package com.example.ms.tweet.mapper.factory

import com.example.ms.tweet.model.dto.SavePostDto
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class PostFactoryTest extends Specification {
    def "BuildPostEntity"() {
        given:
        def dto = EnhancedRandom.random(SavePostDto)

        when:
        def entity = PostFactory.buildPostEntity(dto)

        then:
        entity.title == dto.title
        entity.comments == null
        entity.detail == null
        entity.tags == null
        entity.id == null
    }

    def "BuildFullName"() {
        given:
        def firstName = "Ilkin"
        def lastName = "Aliyev"

        when:
        def actual = PostFactory.buildFullName(firstName, lastName)

        then:
        actual == "Aliyev Ilkin"
    }
}
