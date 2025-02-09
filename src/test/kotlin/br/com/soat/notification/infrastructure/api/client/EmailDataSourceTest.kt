package br.com.soat.notification.infrastructure.api.client

import br.com.soat.notification.core.entities.NotificationRequest
import br.com.soat.notification.infrastructure.exceptions.ResourceInternalServerException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

class EmailDataSourceTest {

    private val javaMailSender: JavaMailSender = mockk()
    private val emailDataSource = EmailDataSource(javaMailSender)

    @Test
    fun `sendEmail should send email and return SimpleMailMessage`() {
        val request = NotificationRequest("user@example.com", "pending", "New Notification")
        every { javaMailSender.send(any<SimpleMailMessage>()) } returns Unit

        val result = emailDataSource.sendEmail(request)

        assertEquals(request.email, result.to?.firstOrNull())
        assertEquals(request.title, result.subject)
        verify(exactly = 1) { javaMailSender.send(any<SimpleMailMessage>()) }
    }

    @Test
    fun `sendEmail should throw ResourceInternalServerException when JavaMailSender fails`() {
        val request = NotificationRequest("user@example.com", "pending", "New Notification")
        every { javaMailSender.send(any<SimpleMailMessage>()) } throws Exception("Failed to send email")

        val exception = assertThrows<ResourceInternalServerException> {
            emailDataSource.sendEmail(request)
        }

        assertEquals("Failed to send email ${request.email}.", exception.message)
        verify(exactly = 1) { javaMailSender.send(any<SimpleMailMessage>()) }
    }
}