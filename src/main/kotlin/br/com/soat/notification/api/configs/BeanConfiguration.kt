package br.com.soat.notification.api.configs

import br.com.soat.notification.core.usecase.SendEmailUseCase
import br.com.soat.notification.infrastructure.api.EmailDataSource
import br.com.soat.notification.infrastructure.gateways.EmailGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender

@Configuration
class BeanConfiguration(val javaMailSender: JavaMailSender) {

    @Bean
    fun emailDataSource(): EmailDataSource {
        return EmailDataSource(javaMailSender)
    }

    @Bean
    fun emailGateway(): EmailGateway {
        return EmailGateway(emailDataSource())
    }

    @Bean
    fun sendMailUseCase(): SendEmailUseCase {
        return SendEmailUseCase(emailGateway())
    }

}