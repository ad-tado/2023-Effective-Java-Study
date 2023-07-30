package org.adtado.dhrdlxl.effective.item3.assignment;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class Presley {
    public static final Presley INSTANCE = new Presley();

    private Presley() {
        if (Objects.nonNull(INSTANCE)) {
            throw new IllegalStateException("Instance already exists.");
        }
    }
}
