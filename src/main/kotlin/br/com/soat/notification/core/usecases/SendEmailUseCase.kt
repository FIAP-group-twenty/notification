package br.com.soat.notification.core.usecases

import br.com.soat.notification.core.entities.Notification
import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.gateways.IEmailGateway

class SendEmailUseCase(private val gateway: IEmailGateway) {

    fun execute(notification: NotificationRequest): Notification {
        return gateway.sendEmail(notification)
    }

}