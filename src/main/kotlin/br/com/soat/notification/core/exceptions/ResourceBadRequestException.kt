package br.com.soat.notification.core.exceptions

import br.com.soat.notification.infrastructure.api.entities.ErrorResponse

class ResourceBadRequestException(message: String, exception: Exception? = null) :
    RuntimeException(message, exception) {

    fun formatter(): ErrorResponse = ErrorResponse(message = message, detail = cause?.message)

}