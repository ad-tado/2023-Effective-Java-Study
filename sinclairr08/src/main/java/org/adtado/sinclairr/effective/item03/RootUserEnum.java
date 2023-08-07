package org.adtado.sinclairr.effective.item03;

public enum RootUserEnum {
    INSTANCE("admin");

    private String name;

    RootUserEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
