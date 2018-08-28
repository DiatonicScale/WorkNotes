package diatonicscale.worknotes.model;

import java.time.LocalDateTime;
import java.util.List;

public class Note {
    private Integer id;
    private Integer parentCategoryId;

    private String name;
    //private List<Source> sources; // TODO: adding information source (book, person, blog) + filter by source
    //private boolean importance = false;
    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    private String value;

    public Note(Integer parentCategoryId, String name, LocalDateTime creationTime, LocalDateTime lastEditTime, String value) {
        this(null, parentCategoryId, name, creationTime, lastEditTime, value);
    }

    public Note(Integer id, Integer parentCategoryId, String name, LocalDateTime creationTime, LocalDateTime lastEditTime, String value) {
        this.id = id;
        this.parentCategoryId = parentCategoryId;
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

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public String getCreationTime() {
        return creationTime == null ? "" : creationTime.toString(); // TODO
    }

    public String getLastEditTime() {
        return lastEditTime.toString(); // TODO
    }
}
