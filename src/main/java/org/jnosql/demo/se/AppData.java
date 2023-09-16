/*
 *    Copyright 2023  Contributors to the Eclipse Foundation
 *
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    and Apache License v2.0 which accompanies this distribution.
 *    The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *    and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *    You may elect to redistribute this code under either of these licenses.
 *
 *    Contributors:
 *
 *    Maximillian Arruda
 *
 */

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
