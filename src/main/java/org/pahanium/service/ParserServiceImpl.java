package org.pahanium.service;

import org.pahanium.entity.Field;
import org.pahanium.entity.Function;
import org.pahanium.entity.Parser;
import org.pahanium.repository.ParserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ParserServiceImpl implements ParserService {
    @Autowired
    private ParserRepository parserRepository;

    @Override
    public List<Parser> list() {
        return parserRepository.findAll();
    }

    @Override
    public void save(Parser parser) {
        parserRepository.save(parser);
    }

    @Override
    public void delete(long id) {
        parserRepository.delete(id);
    }

    @Override
    public Parser findOne(Long id) {
        Parser parser = parserRepository.findOne(id);
        List<Field> fields;
        if (parser != null && (fields = parser.getFields()) != null) {
            Collections.sort(fields);
            List<Function> functions;
            for (Field field : fields) {
                if ((functions = field.getFunctions()) != null) {
                    Collections.sort(functions);
                }
            }
        }
        return parser;
    }
}
