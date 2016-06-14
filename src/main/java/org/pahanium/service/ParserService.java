package org.pahanium.service;

import org.pahanium.entity.Parser;

import java.util.List;

public interface ParserService {
    List<Parser> list();
    void save(Parser parser);
    void delete(long id);
    Parser findOne(Long id);
}
