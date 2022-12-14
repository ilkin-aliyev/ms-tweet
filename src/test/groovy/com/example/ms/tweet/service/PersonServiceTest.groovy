package com.example.ms.tweet.service

import com.example.ms.tweet.dao.entity.PersonEntity
import com.example.ms.tweet.dao.repository.PersonRepository
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class PersonServiceTest extends Specification {
    private PersonRepository personRepository
    private PersonService personService

    def setup() {
        personRepository = Mock()
        personService = new PersonService(personRepository)
    }

    def "GetPersonSuccess"() {
        given:
        def id = EnhancedRandom.random(Long)
        def entity = EnhancedRandom.random(PersonEntity)

        when:
        def dto = personService.getPerson(id)

        then:
        1 * personRepository.findById(id) >> Optional.of(entity)
        dto.name == entity.name
    }

    def "GetPersonError when person not found"() {
        given:
        def id = EnhancedRandom.random(Long)

        when:
        personService.getPerson(id)

        then:
        1 * personRepository.findById(id) >> Optional.empty()
        RuntimeException ex = thrown()
        ex.message == "PERSON_NOT_FOUND"
    }
}
