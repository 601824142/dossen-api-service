package com.dossen.enums;


import org.springframework.util.StringUtils;

public enum Scheme {
    HTTP("HTTP://"),
    HTTPS("HTTPS://"),
    WEBSOCKET("WS://");

    String value;

    private Scheme(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }

    public static Scheme getSchemeByName(String name) {
        if (StringUtils.isEmpty( name )) {
            return Scheme.HTTP;
        }

        for (Scheme scheme : Scheme.values()) {
            if (scheme.getValue().equals(scheme)) {
                return scheme;
            }
        }
        return Scheme.HTTP;
    }

}
