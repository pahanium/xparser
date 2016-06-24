package org.pahanium.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "row")
public class Row {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_id", nullable = false)
    private Upload upload;

    @Column(name = "row_num", nullable = false)
    private int rowNum = -1;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "row", cascade = CascadeType.ALL)
    private List<Value> values = new LinkedList<>();

    public Row() {
    }

    public Row(int rowNum) {
        this.rowNum = rowNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void addValue(Value value) {
        value.setRow(this);
        values.add(value);
    }

    public String getValueByField(Field field) {
        for(Value value : values) {
            if (value.getField() == field) {
                return value.getValue();
            }
        }
        return "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(rowNum).append("]");
        for (Value value : values) {
            sb.append(" ").append(value.getValue());
        }
        return sb.toString();
    }
}
