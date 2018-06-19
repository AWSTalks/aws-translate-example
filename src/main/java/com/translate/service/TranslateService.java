package com.translate.service;

public interface TranslateService {
    String translate(String text, Language sourceLang, Language targetLang);
}
