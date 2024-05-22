package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
// создаем контекстп прилоежния который содержит все определенне бины из класса эппконфиг
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Knight knight = (Knight) applicationContext.getBean(args[0]);
        knight.fight();


    }
}