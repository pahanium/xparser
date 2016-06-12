package org.pahanium.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "field")
public class Field {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(name="field_function",
            joinColumns = @JoinColumn(name="function_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="field_id", referencedColumnName="id")
    )
    private Set<Function> functions;

    private int weight = 0;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parser_id", nullable = false)
    private Parser parser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
