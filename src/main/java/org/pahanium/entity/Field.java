package org.pahanium.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "field")
public class Field {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(name = "field_function",
            joinColumns = @JoinColumn(name = "function_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "field_id", referencedColumnName = "id")
    )
    private Set<Function> functions;

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

    public Set<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(Set<Function> functions) {
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
}
