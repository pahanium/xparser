package org.pahanium.function;

import java.util.HashMap;

public class MultiplicationFunction implements BaseFunction {

    @Override
    public String run(String value, String param, HashMap<String, String> vals) {
        Double d = Double.valueOf(value) * Double.valueOf(param);
        return d.toString();
    }
}
