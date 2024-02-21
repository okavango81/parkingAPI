package com.okavango.parkingapi.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.TimeZone;
@Configuration
public class SpringTimezoneLocaleConfig {

    @PostConstruct
    public void timezoneConfig(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

    @PostConstruct
    public void localeConfig(){
        Locale locale = Locale.forLanguageTag("pt-BR");
        Locale.setDefault(locale);
    }
}
