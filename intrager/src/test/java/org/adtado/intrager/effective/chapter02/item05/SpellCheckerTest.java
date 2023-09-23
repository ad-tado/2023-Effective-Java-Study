package org.adtado.intrager.effective.chapter02.item05;

import org.adtado.intrager.effective.chapter02.item05.SpellChecker;
import org.adtado.intrager.effective.chapter02.item05.factory.EnglishDictionaryFactory;
import org.adtado.intrager.effective.chapter02.item05.factory.KoreanDictionaryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpellCheckerTest {

    @DisplayName("지원하는 언어에 따른 맞춤법 검사기의 언어 유효성이 검증된다")
    @Test
    void isValid() {
        // given
        String korean = "사전";
        String english = "dictionary";

        // when
        SpellChecker koreanChecker = new SpellChecker(KoreanDictionaryFactory::new);
        SpellChecker englishChecker = new SpellChecker(EnglishDictionaryFactory::new);

        // then
        assertTrue(koreanChecker.isValid((korean)));
        assertFalse(koreanChecker.isValid(english));

        assertTrue(englishChecker.isValid(english));
        assertFalse(englishChecker.isValid(korean));
    }

    @DisplayName("검색한 단어가 사전이라는 단어와 똑같다")
    @Test
    void correctDictionary() {
        // given
        String korean = "사전";
        String english = "dictionary";

        // when
        SpellChecker koreanChecker = new SpellChecker(KoreanDictionaryFactory::new);
        SpellChecker englishChecker = new SpellChecker(EnglishDictionaryFactory::new);

        // then
        assertTrue(koreanChecker.correctDictionary(korean));
        assertFalse(koreanChecker.correctDictionary(english));

        assertTrue(englishChecker.correctDictionary(english));
        assertFalse(englishChecker.correctDictionary(korean));
    }

}