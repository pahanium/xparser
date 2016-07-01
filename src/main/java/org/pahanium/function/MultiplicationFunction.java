package org.pahanium.function;

public class MultiplicationFunction implements BaseFunction {

    @Override
    public String run(String value, String param) {
        Double d = Double.valueOf(value) * Double.valueOf(param);
        return d.toString();
    }
}
