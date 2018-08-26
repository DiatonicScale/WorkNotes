package diatonicscale.worknotes.model;

import java.time.LocalDateTime;
import java.util.List;

public class Note {
    private Integer id;
    private Category parentCategory;

    private String name;
    private List<Source> sources; // TODO: adding information source (book, person, blog) + filter by source
    private boolean importance = false;
    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    private String value;

    public Note(Category parentCategory, String name, List<Source> sources, boolean importance, LocalDateTime creationTime, LocalDateTime lastEditTime, String value) {
        this(null, parentCategory, name, sources, importance, creationTime, lastEditTime, value);
    }

    public Note(Integer id, Category parentCategory, String name, List<Source> sources, boolean importance, LocalDateTime creationTime, LocalDateTime lastEditTime, String value) {
        this.id = id;
        this.parentCategory = parentCategory;
        this.name = name;
        this.sources = sources;
        this.importance = importance;
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
}
