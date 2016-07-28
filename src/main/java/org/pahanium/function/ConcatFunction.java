package org.pahanium.function;

import org.pahanium.exception.SkipException;

import java.util.HashMap;

public class ConcatFunction implements BaseFunction {
    @Override
    public String run(String value, String param, HashMap<String, String> vals) throws SkipException {
        StringBuilder sb = new StringBuilder();
        String[] parts = param.split(";");
        for (String part : parts) {
            if (part.startsWith("{")) {
                part = part.substring(1, part.length() - 1);
                if (part.equals("value")) {
                    sb.append(value);
                    continue;
                }

                sb.append(vals.get(part));
            } else {
                sb.append(part);
            }
        }
        return sb.toString();
    }
}
