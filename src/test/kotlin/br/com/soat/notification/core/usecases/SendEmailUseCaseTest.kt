package br.com.soat.notification.core.usecases

import br.com.soat.notification.core.entities.Notification
import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.gateways.IEmailGateway
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SendEmailUseCaseTest {

    private val gateway: IEmailGateway = mockk()
    private val sendEmailUseCase = SendEmailUseCase(gateway)

    @Test
    fun `execute should call sendEmail on gateway and return Notification`() {
        val request = NotificationRequest("user@example.com", "pending", "New Notification")
        val expectedNotification = Notification("user@example.com", "sent")

        every { gateway.sendEmail(request) } returns expectedNotification

        val result = sendEmailUseCase.execute(request)

        assertEquals(expectedNotification, result)
        verify(exactly = 1) { gateway.sendEmail(request) }
    }
}