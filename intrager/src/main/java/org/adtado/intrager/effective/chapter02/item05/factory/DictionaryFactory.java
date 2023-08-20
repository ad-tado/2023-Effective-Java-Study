package org.adtado.intrager.effective.chapter02.item05.factory;

import org.adtado.intrager.effective.chapter02.item05.dictionary.Dictionary;

import java.util.function.Supplier;

public interface DictionaryFactory {

    Supplier<Dictionary> getDictionary();
}
