package br.com.soat.notification.api.controllers

import br.com.soat.notification.core.entities.Notification
import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.usecase.SendEmailUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/send")
class NotificationController(
    private val sendMailUseCase: SendEmailUseCase
) {

    @PostMapping
    fun sendNotification(@RequestBody notification: NotificationRequest): ResponseEntity<Notification> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(sendMailUseCase.execute(notification))
    }
}