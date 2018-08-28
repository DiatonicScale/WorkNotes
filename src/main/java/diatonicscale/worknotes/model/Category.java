package diatonicscale.worknotes.model;

import java.time.LocalDateTime;
import java.util.List;

public class Category {
    private Integer id;

    private Integer userId;

    private String name;

    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    public Category(int userId, String name, LocalDateTime creationTime, LocalDateTime lastEditTime) {
        this(null, userId, name, creationTime, lastEditTime);
    }

    public Category(Integer id, int userId, String name, LocalDateTime creationTime, LocalDateTime lastEditTime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.creationTime = creationTime;
        this.lastEditTime = lastEditTime;
    }

    public String getCreationTime() {
        return creationTime == null ? "" : creationTime.toString(); // TODO
    }

    public String getLastEditTime() {
        return lastEditTime.toString(); // TODO
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }
}
