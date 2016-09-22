package org.pahanium.function;

import java.util.HashMap;

public class MultiplicationFunction implements BaseFunction {

    @Override
    public String run(String value, String param, HashMap<String, String> vals) {
        Double res;
        try {
            res = Double.valueOf(value) * Double.valueOf(param);
        } catch (NumberFormatException e) {
            return value;
        }
        return res.toString();
    }
}
