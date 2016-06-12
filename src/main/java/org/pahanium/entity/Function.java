package org.pahanium.entity;

import org.pahanium.entity.enums.FunctionType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "function")
public class Function {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FunctionType type;

    private String params;

    private int weight;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "functions")
    private Set<Field> fields;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FunctionType getType() {
        return type;
    }

    public void setType(FunctionType type) {
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

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }
}
