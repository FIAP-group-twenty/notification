package br.com.soat.notification.infrastructure.api

import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.infrastructure.exceptions.ResourceInternalServerException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class EmailDataSource(private val javaMail: JavaMailSender) : IEmailDataSource {

    override fun sendEmail(request: NotificationRequest): SimpleMailMessage {
        try {
            val message = SimpleMailMessage()
            message.from = "lucasdrop169@gmail.com"
            message.setTo(request.email)
            message.subject = request.title
            message.text = "Todo troxa recebe esse email"
            javaMail.send(message)
            return message
        } catch (ex: Exception) {
            throw ResourceInternalServerException("Failed to send email ${request.email}.", ex)
        }
    }
}