package org.example;

import org.example.config.AplicationConfiguration;
import org.example.util.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
 public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AplicationConfiguration.class);
    Facade facade =context.getBean(Facade.class);
    facade.readAllUsers();
    facade.readAllTrainees();
facade.readALLTrainers();
facade.readAllTrainings();;
facade.readAllTrainingTypes();

    }
}
