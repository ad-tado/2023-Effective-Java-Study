package org.adtado.intrager.effective.chapter02.item05.factory;

import org.adtado.intrager.effective.chapter02.item05.dictionary.Dictionary;
import org.adtado.intrager.effective.chapter02.item05.dictionary.EnglishDictionary;

import java.util.function.Supplier;

public class EnglishDictionaryFactory implements DictionaryFactory {
    @Override
    public Supplier<Dictionary> getDictionary() {
        return EnglishDictionary::new;
    }
}
