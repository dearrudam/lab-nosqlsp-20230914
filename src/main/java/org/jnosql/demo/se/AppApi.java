package org.jnosql.demo.se;

import com.github.javafaker.Faker;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.communication.document.DocumentEntity;
import org.eclipse.jnosql.communication.document.DocumentManager;
import org.eclipse.jnosql.communication.document.DocumentQuery;

import java.util.UUID;

public class AppApi {

    public static void main(String[] args) {

        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            var manager = container.select(DocumentManager.class).get();

            if (manager.count(DocumentQuery.builder().select()
                    .from("Developer")
                    .build()) == 0) {
                for (int i = 0; i < 100; i++) {
                    var presenter = DocumentEntity.of("Developer");
                    presenter.add("_id", UUID.randomUUID().toString());
                    presenter.add("name", faker.name().fullName());
                    manager.insert(presenter);
                }
            }

            var developers = manager.select(DocumentQuery.builder()
                    .select()
                    .from("Developer")
                    .build()).toList();

            developers.forEach(System.out::println);


        }

    }
}
