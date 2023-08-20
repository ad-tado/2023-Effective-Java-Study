package org.adtado.intrager.effective.chapter02.item05.dictionary;

public class EnglishDictionary implements Dictionary {
    @Override
    public boolean contains(String word) {
        boolean isContained = true;
        for(char morph : word.toCharArray()) {
            if(isNotEnglishCharacter(morph)) {
                isContained = false;
                break;
            }
        }
        return isContained;
    }

    @Override
    public boolean isCorrect(String typo) {
        return typo.equals("dictionary");
    }

    private boolean isNotEnglishCharacter(char code) {
        return (code < 48 || code > 57) && (code < 65 || code > 122);
    }
}
