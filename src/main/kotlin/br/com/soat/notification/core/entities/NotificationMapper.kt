package br.com.soat.notification.core.entities

import br.com.soat.notification.core.entities.Status
import org.springframework.mail.SimpleMailMessage

object NotificationMapper {

    fun SimpleMailMessage.toDTO() = Notification(this.to?.firstOrNull(), Status.SUCCESS.name)
}