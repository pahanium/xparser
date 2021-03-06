package org.pahanium.entity;

import org.springframework.util.AutoPopulatingList;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "field")
public class Field implements Comparable<Field> {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Function> functions = new AutoPopulatingList<>(Function.class);

    @Column(name = "col")
    private int column = 0;

    private int weight = 0;

    @Transient // means "not a DB field"
    private int remove;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parser_id", nullable = false)
    private Parser parser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field", cascade = CascadeType.ALL)
    private List<Value> values;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    @Override
    public int compareTo(Field o) {
        return weight - o.weight;
    }
}
