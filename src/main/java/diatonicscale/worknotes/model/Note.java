package diatonicscale.worknotes.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Note {
    private Integer id;
    private Integer categoryId;

    private String name;
    //private List<Source> sources; // TODO: adding information source (book, person, blog) + filter by source
    //private boolean importance = false;
    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    private String value;

    public Note() {}

    public Note(Integer id, Integer categoryId, String name, String value) {
        this(id, categoryId, name, null, null, value);
    }

    public Note(Integer categoryId, String name, LocalDateTime creationTime, LocalDateTime lastEditTime, String value) {
        this(null, categoryId, name, creationTime, lastEditTime, value);
    }

    public Note(Integer id, Integer categoryId, String name, LocalDateTime creationTime, LocalDateTime lastEditTime, String value) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        //this.sources = sources;
        //this.importance = importance;
        this.creationTime = creationTime;
        this.lastEditTime = lastEditTime;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCreationTime() {
        return creationTime == null ? "" : creationTime.toString(); // TODO
    }

    public String getLastEditTime() {
        return lastEditTime.toString(); // TODO
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = Timestamp.valueOf(creationTime).toLocalDateTime();
    }

    public void setLastEditTime(String lastEditTime) {
        this.lastEditTime = Timestamp.valueOf(lastEditTime).toLocalDateTime();
    }

    public void setValue(String value) {
        this.value = value;
    }
}
