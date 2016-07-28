package org.pahanium.function;

import java.util.HashMap;

public class TrimFunction implements BaseFunction {

    @Override
    public String run(String value, String param, HashMap<String, String> vals) {
        return value.trim();
    }
}
