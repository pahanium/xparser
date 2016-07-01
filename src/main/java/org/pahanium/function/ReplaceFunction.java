package org.pahanium.function;

public class ReplaceFunction implements BaseFunction {

    @Override
    public String run(String value, String param) {
        String[] parts = param.split(";");
        return value.replaceAll(parts[0], parts[1]);
    }
}
