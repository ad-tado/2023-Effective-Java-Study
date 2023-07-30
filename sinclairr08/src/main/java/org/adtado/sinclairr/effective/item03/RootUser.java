package org.adtado.sinclairr.effective.item03;

public class RootUser {
    private static final RootUser INSTANCE = new RootUser("admin");

    private String name;

    private RootUser(String name) {
        this.name = name;
    }

    public static RootUser getInstance() {
        return INSTANCE;
    }

    public String getName() {
        return name;
    }
}
