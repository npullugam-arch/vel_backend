package com.veltrixix.veltrixix_backend.util;

import java.util.UUID;

public final class ReferenceIdGenerator {

    private ReferenceIdGenerator() {
    }

    public static String generate(String prefix) {
        String random = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        return prefix + "-" + random;
    }
}