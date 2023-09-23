package org.adtado.intrager.effective.chapter02.item05.dictionary;

public class KoreanDictionary implements Dictionary {

    @Override
    public boolean contains(String word) {
        boolean isContained = true;
        for(char morph : word.toCharArray()) {
            if(isNotKoreanCharacter(morph)) {
                isContained = false;
                break;
            }
        }
        return isContained;
    }

    @Override
    public boolean isCorrect(String typo) {
        return typo.equals("사전");
    }

    private boolean isNotKoreanCharacter(char code) {
        return code < 44032 || code > 55296;
    }
}
