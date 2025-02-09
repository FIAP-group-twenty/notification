package br.com.soat.notification.core.usecases

import br.com.soat.notification.core.entities.Notification
import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.entities.Status
import br.com.soat.notification.core.gateways.IEmailGateway

class SendEmailUseCase(private val gateway: IEmailGateway) {

    fun execute(notification: NotificationRequest): Notification {
        when (notification.statusProcess) {
            Status.SUCCESS -> {
                notification.title = "Conversão Concluída!"
                notification.message = "Sua solicitação de conversão está pronta. Aproveite!"
            }

            Status.PENDING -> {
                notification.title = "Conversão pendente!"
                notification.message =
                    "Sua solicitação de conversão está sendo processada, assim que estiver pronta entraremos em contato com você por este mesmo e-mail."
            }

            else -> {
                notification.title = "Conversão falhou!"
                notification.message =
                    "Tivemos um probleminha com a sua solicitação de conversão, por favor faça uma nova tentativa."
            }

        }
        return gateway.sendEmail(notification)
    }

}