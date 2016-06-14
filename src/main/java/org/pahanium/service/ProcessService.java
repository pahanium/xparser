package org.pahanium.service;

import org.pahanium.entity.Parser;

import java.io.File;

public interface ProcessService {
    void parse (File file, Parser parser) throws Exception;
}
