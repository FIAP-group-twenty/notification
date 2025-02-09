package br.com.soat.notification.core.entities

data class NotificationRequest(
    val email: String,
    val statusProcess: String,
    val title: String,
)
