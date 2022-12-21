package com.example.ms.tweet.service

import com.example.ms.tweet.dao.entity.PersonEntity
import com.example.ms.tweet.dao.repository.PersonRepository
import com.example.ms.tweet.util.PersonUtil
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification
import spock.lang.Unroll

class PersonServiceTest extends Specification {
//    private PersonRepository personRepository
//    private PersonService personService
//    private PersonUtil personUtil
//
//    def setup() {
//        personRepository = Mock()
//        personUtil = Mock()
//        personService = new PersonService(personRepository, personUtil)
//    }
//
//    @Unroll
//    def "GetPersonSuccess"() {
//        given:
//        def id = EnhancedRandom.random(Long)
//        def entity = EnhancedRandom.random(PersonEntity)
//        entity.id = personId
//
//        when:
//        def dto = personService.getPerson(id)
//
//        then:
//        1 * personRepository.findById(id) >> Optional.of(entity)
//        callsCount * personUtil.checkAge(entity.age)
//        dto.name == entity.name
//
//        where:
//        callsCount | personId
//        0          | 1L
//        1          | null
//    }
//
//    def "GetPersonError when person not found"() {
//        given:
//        def id = EnhancedRandom.random(Long)
//
//        when:
//        personService.getPerson(id)
//
//        then:
//        1 * personRepository.findById(id) >> Optional.empty()
//        0 * personUtil.checkAge(_)
//        RuntimeException ex = thrown()
//        ex.message == "PERSON_NOT_FOUND"
//    }
}
