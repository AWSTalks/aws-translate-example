package com.translate

import com.translate.service.Language
import com.translate.service.TranslateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class TranslateServiceTest extends Specification {
    @Autowired
    TranslateService translateService

    def "translate text expected succeed"() {
        when:
        def result = translateService.translate("Hello World", Language.ENGLISH, Language.SPANISH)
        then:
        result == "Hola Mundo"
    }

    def "translate text wrong source and target language expected null"() {
        when:
        def result = translateService.translate("Bonjour le monde", Language.FRENCH, Language.SPANISH)
        then:
        result == null
    }

    def "translate text expected null"() {
        when:
        def result = translateService.translate("", Language.ENGLISH, Language.SPANISH)
        then:
        result == null
    }
}
