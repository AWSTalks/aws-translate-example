package com.translate.service;

import lombok.Getter;

@Getter
public enum Language {
    ARABIC("ar"),
    CHINESE("zh"),
    ENGLISH("en"),
    FRENCH("fr"),
    GERMAN("de"),
    PORTUGUESE("pt"),
    SPANISH("es");

    private String langCode;

    Language(String langCode) {
        this.langCode = langCode;
    }
}
