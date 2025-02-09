package br.com.soat.notification.api.controller

import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.usecase.SendEmailUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/send")
class SendNotificationController(
    private val sendMailUseCase: SendEmailUseCase
) {

    @PostMapping
    fun sendNotification(@RequestBody notification: NotificationRequest){
        sendMailUseCase.execute(notification)
    }
}