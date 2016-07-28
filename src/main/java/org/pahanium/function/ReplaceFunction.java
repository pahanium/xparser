package org.pahanium.function;

import java.util.HashMap;

public class ReplaceFunction implements BaseFunction {

    @Override
    public String run(String value, String param, HashMap<String, String> vals) {
        String[] parts = param.split(";");
        return value.replaceAll(parts[0], parts[1]);
    }
}
