package com.icebirdtech.dmp.modal;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Decision enum.
 */
public enum Decision {

    APPROVED("APPROVED"),
    DECLINED("DECLINED");
    private final String value;
    private final static Map<String, Decision> CONSTANTS = new HashMap<String, Decision>();

    static {
        for (Decision c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    Decision(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static Decision fromValue(String value) {
        Decision constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
