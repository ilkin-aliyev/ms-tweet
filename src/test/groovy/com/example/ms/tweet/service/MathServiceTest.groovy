package com.example.ms.tweet.service

import spock.lang.Specification
import spock.lang.Unroll

class MathServiceTest extends Specification {
    private MathService mathService

    def setup() {
        mathService = new MathService()
    }

    def "TestSumSuccess"() {
        given:
        def firstElement = BigDecimal.ONE
        def secondElement = BigDecimal.valueOf(4)

        when:
        def actual = mathService.sum(firstElement, secondElement)

        then:
        actual == BigDecimal.valueOf(5)
    }

    @Unroll
    def "TestSumError"() {
        given:
        def firstElement = a
        def secondElement = b

        when:
        mathService.sum(firstElement, secondElement)

        then:
        RuntimeException ex = thrown()
        ex.message == "Elements can't be null"

        where:
        a                     | b
        null                  | BigDecimal.valueOf(5)
        BigDecimal.valueOf(5) | null
        null                  | null
    }
}