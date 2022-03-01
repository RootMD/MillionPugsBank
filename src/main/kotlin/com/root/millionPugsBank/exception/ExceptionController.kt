package com.root.millionPugsBank.exception

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.RestClientException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.ZonedDateTime

@ControllerAdvice
class ExceptionController : ResponseEntityExceptionHandler() {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(ZonedDateTime.now(), HttpStatus.NOT_FOUND, e.message)
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(e: IllegalArgumentException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(ZonedDateTime.now(), HttpStatus.BAD_REQUEST, e.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidFormatException::class)
    fun handleInvalidFormat(e: InvalidFormatException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(ZonedDateTime.now(), HttpStatus.BAD_REQUEST, e.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(RestClientException::class)
    fun handleRestClient(e: RestClientException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(ZonedDateTime.now(), HttpStatus.SERVICE_UNAVAILABLE, e.message)
        return ResponseEntity(errorMessage, HttpStatus.SERVICE_UNAVAILABLE)
    }
}