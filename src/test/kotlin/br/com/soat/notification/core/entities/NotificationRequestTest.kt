package br.com.soat.notification.core.entities

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class NotificationRequestTest {

    private val email = "user@example.com"
    private val statusProcess = Status.PENDING
    private val title = "New Notification"
    private val message = "New message"

    @Test
    fun `test NotificationRequest creation`() {
        val notificationRequest = newRequest()

        assertEquals(email, notificationRequest.email)
        assertEquals(statusProcess, notificationRequest.statusProcess)
        assertEquals(title, notificationRequest.title)
    }

    @Test
    fun `test NotificationRequest equality`() {
        val notificationRequest1 = newRequest()
        val notificationRequest2 = newRequest()

        assertEquals(notificationRequest1, notificationRequest2)
        assertEquals(notificationRequest1.hashCode(), notificationRequest2.hashCode())
    }

    @Test
    fun `test NotificationRequest immutability`() {
        val notificationRequest = newRequest()
        val newNotificationRequest = notificationRequest.copy(statusProcess = Status.SUCCESS)

        assertEquals("user@example.com", newNotificationRequest.email)
        assertEquals(Status.SUCCESS, newNotificationRequest.statusProcess)
        assertEquals("New Notification", newNotificationRequest.title)
        assertNotEquals(notificationRequest, newNotificationRequest)
    }

    @Test
    fun `test NotificationRequest toString`() {
        val notificationRequest = newRequest()
        val toStringResult = notificationRequest.toString()

        assertTrue(toStringResult.contains("user@example.com"))
        assertTrue(toStringResult.contains(Status.PENDING.toString()))
        assertTrue(toStringResult.contains("New Notification"))
    }

    private fun newRequest(): NotificationRequest {
        return NotificationRequest(email, statusProcess, title, message)
    }
}