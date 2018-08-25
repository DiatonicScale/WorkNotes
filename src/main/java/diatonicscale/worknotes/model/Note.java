package diatonicscale.worknotes.model;

import java.time.LocalDateTime;
import java.util.List;

public class Note {
    private int id;
    private Category parentCategory;

    private String name;
    private List<Source> sources; // TODO: adding information source (book, person, blog) + filter by source
    private boolean importance = false;
    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    private String value;
}
