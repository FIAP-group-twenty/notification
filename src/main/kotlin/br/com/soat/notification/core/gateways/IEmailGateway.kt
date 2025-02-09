package br.com.soat.notification.core.gateways

import br.com.soat.notification.core.entities.Notification
import br.com.soat.notification.core.entities.NotificationRequest

interface IEmailGateway {
    fun sendEmail(request: NotificationRequest): Notification
}