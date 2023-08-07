package org.adtado.katfun.effective.chapter02.item01.response;

public enum Item01UserCategory {
    VSFE("보초"),
    KATFUN("카펀"),
    VERTICALCODE("바티칸코드"),
    WUGI("우기"),
    IVAI("이바이"),
    CHAK("착"),
    BRUCE_HAN("불산"),
    HARRY_POTTA("포타봉고");

    private final String username;

    Item01UserCategory(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
