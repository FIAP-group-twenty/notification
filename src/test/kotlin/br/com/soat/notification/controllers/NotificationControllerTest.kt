package br.com.soat.notification.controllers

import br.com.soat.notification.api.controllers.NotificationController
import br.com.soat.notification.core.entities.Notification
import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.entities.Status
import br.com.soat.notification.core.usecases.SendEmailUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class NotificationControllerTest {

    private val sendMailUseCase: SendEmailUseCase = mockk()
    private val controller = NotificationController(sendMailUseCase)

    @Test
    fun `sendNotification should return 201 CREATED and the notification`() {
        val request = NotificationRequest("user@example.com", Status.SUCCESS, "New Notification", "New message")
        val notification = Notification("user@example.com", "sent")

        every { sendMailUseCase.execute(request) } returns notification

        val response: ResponseEntity<Any> = controller.sendNotification(request)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(notification, response.body)
        verify(exactly = 1) { sendMailUseCase.execute(request) }
    }
}