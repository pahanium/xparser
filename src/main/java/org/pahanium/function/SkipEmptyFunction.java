package org.pahanium.function;

import org.pahanium.exception.SkipException;

import java.util.HashMap;

public class SkipEmptyFunction implements BaseFunction {
    @Override
    public String run(String value, String param, HashMap<String, String> vals) throws SkipException {
        if (value == null || value.length() == 0) {
            throw new SkipException();
        }

        return value;
    }
}
