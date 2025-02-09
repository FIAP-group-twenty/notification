package br.com.soat.notification.infrastructure.gateways

import br.com.soat.notification.core.entities.Notification
import br.com.soat.notification.core.entities.NotificationMapper.toDTO
import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.gateways.IEmailGateway
import br.com.soat.notification.infrastructure.api.client.IEmailDataSource
import org.springframework.stereotype.Repository

@Repository
class EmailGateway(private val dataSource: IEmailDataSource) : IEmailGateway {

    override fun sendEmail(request: NotificationRequest): Notification {
        val result = dataSource.sendEmail(request)
        return result.toDTO()
    }

}