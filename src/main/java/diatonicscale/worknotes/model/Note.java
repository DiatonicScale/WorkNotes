package diatonicscale.worknotes.model;

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
}
