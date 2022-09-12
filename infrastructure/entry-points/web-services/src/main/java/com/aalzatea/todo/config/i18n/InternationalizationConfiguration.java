package com.aalzatea.todo.config.i18n;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class InternationalizationConfiguration {

    @Value("${app.i18n-configuration.baseNames}")
    private String[] messageBaseNames;

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(messageBaseNames);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
