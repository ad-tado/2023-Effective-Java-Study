package org.adtado.intrager.effective.chapter02.item05;

import org.adtado.intrager.effective.chapter02.item05.dictionary.Dictionary;
import org.adtado.intrager.effective.chapter02.item05.factory.DictionaryFactory;

import java.util.function.Supplier;

public class SpellChecker {
    private Supplier<Dictionary> dictionary;

    public SpellChecker(Supplier<? extends DictionaryFactory> dictionaryFactory) {
        this.dictionary = dictionaryFactory.get().getDictionary();
    }

    public boolean isValid(String word) {
        return dictionary.get().contains(word);
    }

    public boolean correctDictionary(String typo) {
        return dictionary.get().isCorrect(typo);
    }
}
