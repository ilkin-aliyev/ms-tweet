package com.example.ms.tweet.service;

import com.example.ms.tweet.dao.repository.PersonRepository;
import com.example.ms.tweet.model.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public PersonDto getPerson(Long id) {
        var person = personRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("PERSON_NOT_FOUND");
        });

        return new PersonDto(person.getName());
    }
}
