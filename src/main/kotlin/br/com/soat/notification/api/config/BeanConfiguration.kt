package br.com.soat.notification.api.config

import br.com.soat.notification.core.usecase.SendEmailUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender

@Configuration
class BeanConfiguration(
    private val javaMailSender: JavaMailSender,
) {


    @Bean
    fun sendMailUseCase(): SendEmailUseCase {
        return SendEmailUseCase(javaMailSender)
    }

}