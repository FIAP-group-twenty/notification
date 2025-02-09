package br.com.soat.notification.core.entities

data class NotificationRequest(
    val email: String,
    val statusProcess: Status,
    var title: String?,
    var message: String?
)
