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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "field_function",
            joinColumns = @JoinColumn(name = "field_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "function_id", referencedColumnName = "id")
    )
    private List<Function> functions = new AutoPopulatingList<>(Function.class);

    @Column(name = "col")
    private int column = 0;

    private int weight = 0;

    @Transient // means "not a DB field"
    private int remove;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parser_id", nullable = false)
    private Parser parser;

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

    @Override
    public int compareTo(Field o) {
        return weight - o.weight;
    }
}
