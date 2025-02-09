package br.com.soat.notification.infrastructure.api

import br.com.soat.notification.core.entities.NotificationRequest
import org.springframework.mail.SimpleMailMessage

interface IEmailDataSource {
    fun sendEmail(request: NotificationRequest): SimpleMailMessage
}