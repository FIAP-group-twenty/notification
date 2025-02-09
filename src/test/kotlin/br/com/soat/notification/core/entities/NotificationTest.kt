package br.com.soat.notification.core.entities

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class NotificationTest {

    @Test
    fun `test Notification creation`() {
        val email = "user@example.com"
        val status = "unread"

        val notification = Notification(email, status)

        assertEquals(email, notification.email)
        assertEquals(status, notification.status)
    }

    @Test
    fun `test Notification equality`() {
        val notification1 = Notification("user@example.com", "unread")
        val notification2 = Notification("user@example.com", "unread")

        assertEquals(notification1, notification2)
        assertEquals(notification1.hashCode(), notification2.hashCode())
    }

    @Test
    fun `test Notification immutability`() {
        val notification = Notification("user@example.com", "unread")
        val newNotification = notification.copy(status = "read")

        assertEquals("user@example.com", newNotification.email)
        assertEquals("read", newNotification.status)
        assertNotEquals(notification, newNotification)
    }

    @Test
    fun `test Notification toString`() {
        val notification = Notification("user@example.com", "unread")
        val toStringResult = notification.toString()

        assertTrue(toStringResult.contains("user@example.com"))
        assertTrue(toStringResult.contains("unread"))
    }
}