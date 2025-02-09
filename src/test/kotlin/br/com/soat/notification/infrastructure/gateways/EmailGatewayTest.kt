package br.com.soat.notification.infrastructure.gateways

import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.entities.Status
import br.com.soat.notification.infrastructure.api.client.IEmailDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.mail.SimpleMailMessage

class EmailGatewayTest {

    private val dataSource: IEmailDataSource = mockk()
    private val emailGateway = EmailGateway(dataSource)

    @Test
    fun `sendEmail should call sendEmail on dataSource and return Notification`() {
        val request = NotificationRequest("user@example.com", Status.SUCCESS, "New Notification", "New message")
        val simpleMailMessage = SimpleMailMessage().apply {
         setTo(request.email)
        }

        every { dataSource.sendEmail(request) } returns simpleMailMessage
        val result = emailGateway.sendEmail(request)

        assertEquals(request.email, result.email)
        verify(exactly = 1) { dataSource.sendEmail(request) }
    }
}