package org.jnosql.demo.se;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.UUID;

@Entity
public record Developer(@Id String id,@Column String name) {
    public static Developer of(String name) {
        return new Developer(UUID.randomUUID().toString(), name);
    }
}
