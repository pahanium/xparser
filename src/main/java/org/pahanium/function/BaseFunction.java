package org.pahanium.function;

import org.pahanium.exception.SkipException;

import java.util.HashMap;

public interface BaseFunction {

    String run(String value, String param, HashMap<String, String> vals) throws SkipException;
}
