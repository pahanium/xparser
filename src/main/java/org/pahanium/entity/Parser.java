package org.pahanium.entity;

import org.springframework.util.AutoPopulatingList;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "parser")
public class Parser {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "start_line", nullable = false)
    private int startLine = 1;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Field> fields = new AutoPopulatingList<>(Field.class);

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parser")
//    private Set<Field> consts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parser")
    private Set<Upload> uploads;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

//    public Set<Field> getConsts() {
//        return consts;
//    }
//
//    public void setConsts(Set<Field> consts) {
//        this.consts = consts;
//    }

    public Set<Upload> getUploads() {
        return uploads;
    }

    public void setUploads(Set<Upload> uploads) {
        this.uploads = uploads;
    }
}
