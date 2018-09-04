package diatonicscale.worknotes.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Category {
    private Integer id;

    private Integer userId;

    private String name;

    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    public Category () {}

    public Category(Integer id, int userId, String name) {
        this(id, userId, name, null, null);
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
