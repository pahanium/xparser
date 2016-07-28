package org.pahanium.entity;

import org.pahanium.entity.enums.FunctionTypeEnum;
import org.pahanium.exception.SkipException;
import org.pahanium.function.*;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "function")
public class Function implements Comparable<Function> {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FunctionTypeEnum type;

    private String params;

    private int weight;

    @Transient // means "not a DB field"
    private int remove;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FunctionTypeEnum getType() {
        return type;
    }

    public void setType(FunctionTypeEnum type) {
        this.type = type;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    @Override
    public int compareTo(Function o) {
        return weight - o.weight;
    }

    static final BaseFunction trim = new TrimFunction();
    static final BaseFunction replace = new ReplaceFunction();
    static final BaseFunction multiplication = new MultiplicationFunction();
    static final BaseFunction skipEmpty = new SkipEmptyFunction();
    static final BaseFunction concat = new ConcatFunction();

    public String run(String value, HashMap<String, String> vals) throws SkipException {
        switch (type) {
            case TRIM:
                return trim.run(value, params, vals);
            case REPLACE:
                return replace.run(value, params, vals);
            case MULTIPLICATION:
                return multiplication.run(value, params, vals);
            case SKIPEMPTY:
                return skipEmpty.run(value, params, vals);
            case CONCAT:
                return concat.run(value, params, vals);
        }
        return value;
    }
}
