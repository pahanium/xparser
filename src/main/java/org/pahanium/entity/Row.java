package org.pahanium.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "row")
public class Row {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_id", nullable = false)
    private Upload upload;

    private int rowNum;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "row")
    private Set<Value> values;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Upload getUpload() {
        return upload;
    }

    public void setUpload(Upload upload) {
        this.upload = upload;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public Set<Value> getValues() {
        return values;
    }

    public void setValues(Set<Value> values) {
        this.values = values;
    }
}
