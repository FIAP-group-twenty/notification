package br.com.soat.notification.core.usecases

import br.com.soat.notification.core.entities.Notification
import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.core.entities.Status
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
    fun `execute should set title and message for SUCCESS status and call gateway`() {
        val request = NotificationRequest("user@example.com", Status.SUCCESS, "", "")
        val expectedNotification = Notification("user@example.com", "Conversão Concluída!")

        every { gateway.sendEmail(request) } returns expectedNotification

        val result = sendEmailUseCase.execute(request)

        assertEquals(expectedNotification, result, "A notificação retornada deve ser a mesma que o gateway retornou")
        verify(exactly = 1) { gateway.sendEmail(request) }
    }

    @Test
    fun `execute should set title and message for PENDING status and call gateway`() {
        val request = NotificationRequest("user@example.com", Status.PENDING, "", "")
        val expectedNotification = Notification("user@example.com", "Conversão pendente!")

        every { gateway.sendEmail(request) } returns expectedNotification

        val result = sendEmailUseCase.execute(request)

        assertEquals(expectedNotification, result, "A notificação retornada deve ser a mesma que o gateway retornou")
        verify(exactly = 1) { gateway.sendEmail(request) }
    }

    @Test
    fun `execute should set title and message for FAILURE status and call gateway`() {
        val request = NotificationRequest("user@example.com", Status.FAIL, "", "")
        val expectedNotification = Notification("user@example.com", "Conversão falhou!")

        every { gateway.sendEmail(request) } returns expectedNotification

        val result = sendEmailUseCase.execute(request)

        assertEquals(expectedNotification, result, "A notificação retornada deve ser a mesma que o gateway retornou")
        verify(exactly = 1) { gateway.sendEmail(request) }
    }
}