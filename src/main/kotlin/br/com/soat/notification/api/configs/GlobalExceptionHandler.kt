package br.com.soat.notification.api.configs;

import br.com.soat.notification.infrastructure.exceptions.ResourceBadRequestException
import br.com.soat.notification.infrastructure.api.entities.ErrorResponse
import br.com.soat.notification.infrastructure.exceptions.ResourceInternalServerException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleResourceBadRequestException(ex: ResourceBadRequestException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ex.formatter(), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceInternalServerException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleResourceInternalServerException(ex: ResourceInternalServerException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ex.formatter(), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

