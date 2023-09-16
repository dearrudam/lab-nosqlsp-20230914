package org.jnosql.demo.se;

import com.github.javafaker.Faker;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

public class AppData {
    public static void main(String[] args) {

        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            NosqlSP template = container.select(NosqlSP.class, DatabaseQualifier.ofDocument()).get();

            for (int i = 0; i < 100; i++) {
                var presenter = Developer.of(faker.name().fullName());
                template.save(presenter);
            }
            var presenter = Developer.of("Thiago");
            template.save(presenter);

            template.cade("Thiago").forEach(System.out::println);
            template.findByNameLike("Thiago").forEach(System.out::println);
        }

    }
}
