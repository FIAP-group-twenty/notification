package br.com.soat.notification.core.usecase

import br.com.soat.notification.core.entities.NotificationRequest
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender


class SendEmailUseCase(
    private val javaMail: JavaMailSender,
) {

    fun execute(notification: NotificationRequest){
        val message = SimpleMailMessage()
        message.from = "lucasdrop169@gmail.com"
        message.setTo(notification.email)
        message.subject = notification.title
        message.text = "Todo troxa recebe esse email"
        javaMail.send(message)
    }

}