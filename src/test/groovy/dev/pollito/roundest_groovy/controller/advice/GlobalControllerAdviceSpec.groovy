package dev.pollito.roundest_groovy.controller.advice

import org.springframework.data.mapping.PropertyReferenceException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.resource.NoResourceFoundException
import spock.lang.Specification

class GlobalControllerAdviceSpec extends Specification {
    GlobalControllerAdvice globalControllerAdvice = new GlobalControllerAdvice()

    private static void problemDetailAssertions(
            ProblemDetail response, Exception e, HttpStatus httpStatus) {
        assert response.status == httpStatus.value()
        assert response.title == e.getClass().simpleName
        assert response.properties?.get("timestamp") != null
        assert response.properties?.get("trace") != null
    }

    def "when Exception then return ProblemDetail"() {
        given: "a mocked Exception"
        def e = Mock(Exception)

        when: "handling the exception"
        def response = globalControllerAdvice.handle(e)

        then: "the response is a ProblemDetail with INTERNAL_SERVER_ERROR status"
        problemDetailAssertions(response, e, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    def "when IllegalArgumentException then return ProblemDetail"() {
        given: "a mocked IllegalArgumentException"
        def e = Mock(IllegalArgumentException)

        when: "handling the exception"
        def response = globalControllerAdvice.handle(e)

        then: "the response is a ProblemDetail with BAD_REQUEST status"
        problemDetailAssertions(response, e, HttpStatus.BAD_REQUEST)
    }

    def "when MethodArgumentTypeMismatchException then return ProblemDetail"() {
        given: "a mocked MethodArgumentTypeMismatchException"
        def e = Mock(MethodArgumentTypeMismatchException)

        when: "handling the exception"
        def response = globalControllerAdvice.handle(e)

        then: "the response is a ProblemDetail with BAD_REQUEST status"
        problemDetailAssertions(response, e, HttpStatus.BAD_REQUEST)
    }

    def "when NoResourceFoundException then return ProblemDetail"() {
        given: "a mocked NoResourceFoundException"
        def e = Mock(NoResourceFoundException)

        when: "handling the exception"
        def response = globalControllerAdvice.handle(e)

        then: "the response is a ProblemDetail with NOT_FOUND status"
        problemDetailAssertions(response, e, HttpStatus.NOT_FOUND)
    }

    def "when NoSuchElementException then return ProblemDetail"() {
        given: "a mocked NoSuchElementException"
        def e = Mock(NoSuchElementException)

        when: "handling the exception"
        def response = globalControllerAdvice.handle(e)

        then: "the response is a ProblemDetail with NOT_FOUND status"
        problemDetailAssertions(response, e, HttpStatus.NOT_FOUND)
    }

    def "when PropertyReferenceException then return ProblemDetail"() {
        given: "a mocked PropertyReferenceException"
        def e = Mock(PropertyReferenceException)

        when: "handling the exception"
        def response = globalControllerAdvice.handle(e)

        then: "the response is a ProblemDetail with BAD_REQUEST status"
        problemDetailAssertions(response, e, HttpStatus.BAD_REQUEST)
    }
}
