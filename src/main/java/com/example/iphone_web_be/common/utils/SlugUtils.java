package com.example.iphone_web_be.common.utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public final class SlugUtils {

    private SlugUtils() {
    }

    public static String generateSlug(String input) {
        if (input == null) return "";

        String normalized = Normalizer.normalize(
                input,
                Normalizer.Form.NFD
        );

        return Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
                .matcher(normalized)
                .replaceAll("")
                .toLowerCase(Locale.ENGLISH)
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-");
    }
}