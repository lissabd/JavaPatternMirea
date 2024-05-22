package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// используется для настройки конфигурации приложения, включая определения бинов
// для каждой из реализаций интерфейса
public class AppConfig {

    @Bean
    public Knight strongKnight() {
        return new StrongKnight();
    }
    @Bean
    public Knight weakKnight() {
        return new WeakKnight();
    }

    @Bean
    public Knight kingOfKnights() {
        return new KingOfKnights();
    }
}
