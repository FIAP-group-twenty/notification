package br.com.soat.notification.core.entities

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class NotificationRequestTest {

    @Test
    fun `test NotificationRequest creation`() {
        val email = "user@example.com"
        val statusProcess = "pending"
        val title = "New Notification"

        val notificationRequest = NotificationRequest(email, statusProcess, title)

        assertEquals(email, notificationRequest.email)
        assertEquals(statusProcess, notificationRequest.statusProcess)
        assertEquals(title, notificationRequest.title)
    }

    @Test
    fun `test NotificationRequest equality`() {
        val notificationRequest1 = NotificationRequest("user@example.com", "pending", "New Notification")
        val notificationRequest2 = NotificationRequest("user@example.com", "pending", "New Notification")

        assertEquals(notificationRequest1, notificationRequest2)
        assertEquals(notificationRequest1.hashCode(), notificationRequest2.hashCode())
    }

    @Test
    fun `test NotificationRequest immutability`() {
        val notificationRequest = NotificationRequest("user@example.com", "pending", "New Notification")
        val newNotificationRequest = notificationRequest.copy(statusProcess = "completed")

        assertEquals("user@example.com", newNotificationRequest.email)
        assertEquals("completed", newNotificationRequest.statusProcess)
        assertEquals("New Notification", newNotificationRequest.title)
        assertNotEquals(notificationRequest, newNotificationRequest)
    }

    @Test
    fun `test NotificationRequest toString`() {
        val notificationRequest = NotificationRequest("user@example.com", "pending", "New Notification")
        val toStringResult = notificationRequest.toString()

        assertTrue(toStringResult.contains("user@example.com"))
        assertTrue(toStringResult.contains("pending"))
        assertTrue(toStringResult.contains("New Notification"))
    }
}