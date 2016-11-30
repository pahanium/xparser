package org.pahanium.service;

import org.pahanium.entity.Function;
import org.pahanium.exception.SkipException;
import org.pahanium.function.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FunctionService {
    private BaseFunction trim = new TrimFunction();
    private BaseFunction replace = new ReplaceFunction();
    private BaseFunction multiplication = new MultiplicationFunction();
    private BaseFunction skipEmpty = new SkipEmptyFunction();
    private BaseFunction concat = new ConcatFunction();

    public FunctionService() {
    }

    public String run(Function func, String value, HashMap<String, String> vals) throws SkipException {
        switch (func.getType()) {
            case TRIM:
                return trim.run(value, func.getParams(), vals);
            case REPLACE:
                return replace.run(value, func.getParams(), vals);
            case MULTIPLICATION:
                return multiplication.run(value, func.getParams(), vals);
            case SKIPEMPTY:
                return skipEmpty.run(value, func.getParams(), vals);
            case CONCAT:
                return concat.run(value, func.getParams(), vals);
        }
        return value;
    }
}
