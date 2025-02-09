package br.com.soat.notification.core.usecase

import br.com.soat.notification.core.entities.Notification
import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.gateways.IEmailGateway
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

class SendEmailUseCase(private val gateway: IEmailGateway) {

    fun execute(notification: NotificationRequest): Notification {
        return gateway.sendEmail(notification)
    }

}