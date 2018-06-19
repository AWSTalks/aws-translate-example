package com.translate.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import com.translate.config.AwsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class AmazonTranslateImpl implements TranslateService {
    private final AwsConfig config;
    private AmazonTranslate client;

    public AmazonTranslateImpl(AwsConfig config) {
        this.config = config;
    }

    @PostConstruct
    public void init() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(config.getApiKey(), config.getSecretKey());

        client = AmazonTranslateClientBuilder
                .standard()
                .withRegion(Regions.EU_WEST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Override
    public String translate(String text, Language sourceLang, Language targetLang) {
        TranslateTextRequest request = new TranslateTextRequest()
                .withText(text)
                .withSourceLanguageCode(sourceLang.getLangCode())
                .withTargetLanguageCode(targetLang.getLangCode());

        try {
            TranslateTextResult result = client.translateText(request);
            log.info("Text: {}, Source Lang: {}, Target Lang: {}, Result: {}", text, sourceLang, targetLang, result.getTranslatedText());
            return result.getTranslatedText();
        } catch (Exception e) {
            log.error("Amazon Translate Exception:", e);
        }
        return null;
    }
}
