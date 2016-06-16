package org.pahanium.entity;

import org.pahanium.entity.enums.FunctionTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "function")
public class Function {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FunctionTypeEnum type;

    private String params;

    private int weight;

//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "functions")
//    private List<Field> fields;

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

//    public List<Field> getFields() {
//        return fields;
//    }
//
//    public void setFields(List<Field> fields) {
//        this.fields = fields;
//    }
}
