package org.adtado.verticalcode.effective.chap2.item6;

import java.util.ArrayList;
import java.util.List;

public class BigList {
    private List<String> strings;

    public List<String> init() {
        List<String> OOMKiller = new ArrayList<>();
        for(int i = 0; i < 500000; i++) {
            OOMKiller.add(String.valueOf(i));
        }
        return OOMKiller;
    }

    public List<String> init2() {
        List<String> OOMKiller = new ArrayList<>();
        for(int i = 0; i < 500000; i++) {
            OOMKiller.add("i");
        }
        return OOMKiller;
    }
}
