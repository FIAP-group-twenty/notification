package br.com.soat.notification.infrastructure.api.entities

data class ErrorResponse(
    val message: String?,
    val detail: String? = null
)