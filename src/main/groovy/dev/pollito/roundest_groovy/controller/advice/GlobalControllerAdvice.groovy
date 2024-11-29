package dev.pollito.roundest_groovy.controller.advice

import io.opentelemetry.api.trace.Span
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.resource.NoResourceFoundException

import groovy.util.logging.Slf4j

import java.time.Instant
import java.time.format.DateTimeFormatter

@RestControllerAdvice
@Slf4j
class GlobalControllerAdvice {

    @ExceptionHandler(NoResourceFoundException)
    ProblemDetail handle(NoResourceFoundException e) {
        buildProblemDetail(e, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException)
    ProblemDetail handle(MethodArgumentNotValidException e) {
        buildProblemDetail(e, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException)
    ProblemDetail handle(MethodArgumentTypeMismatchException e) {
        buildProblemDetail(e, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NoSuchElementException)
    ProblemDetail handle(NoSuchElementException e) {
        buildProblemDetail(e, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception)
    ProblemDetail handle(Exception e) {
        buildProblemDetail(e, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private static ProblemDetail buildProblemDetail(Exception e, HttpStatus status) {
        String exceptionSimpleName = e.class.simpleName
        log.error("${exceptionSimpleName} being handled", e)

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.localizedMessage)
        problemDetail.title = exceptionSimpleName
        problemDetail.setProperty("timestamp", DateTimeFormatter.ISO_INSTANT.format(Instant.now()))
        problemDetail.setProperty("trace", Span.current().spanContext.traceId)
        return problemDetail
    }
}