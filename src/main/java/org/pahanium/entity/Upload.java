package org.pahanium.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "upload")
public class Upload {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String filename;

    //@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parser_id", nullable = false)
    private Parser parser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "upload", cascade = CascadeType.ALL)
    private List<Row> rows = new LinkedList<>();

    public Upload() {
    }

    public Upload(String filename, Parser parser) {
        this.filename = filename;
        this.parser = parser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public void addRow(Row newRow) {
        newRow.setUpload(this);
        rows.add(newRow);
    }

    @PrePersist
    void createdAt() {
        this.date = new Date();
    }
}
