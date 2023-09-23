package org.adtado.intrager.effective.chapter02.item05.factory;

import org.adtado.intrager.effective.chapter02.item05.dictionary.Dictionary;
import org.adtado.intrager.effective.chapter02.item05.dictionary.KoreanDictionary;

import java.util.function.Supplier;

public class KoreanDictionaryFactory implements DictionaryFactory {
    @Override
    public Supplier<Dictionary> getDictionary() {
        return KoreanDictionary::new;
    }
}
