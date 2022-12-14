package com.example.ms.tweet.dao.repository;

import com.example.ms.tweet.dao.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
