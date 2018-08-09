package diatonicscale.worknotes.model;

import java.time.LocalDateTime;
import java.util.List;

public class Category {
    private int id;
    private int parentId; // Adjacency List

    private String name;

    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;


}
