package org.pahanium.function;

public class TrimFunction implements BaseFunction {

    @Override
    public String run(String value, String param) {
        return value.trim();
    }
}
