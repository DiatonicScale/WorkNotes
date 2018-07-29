package ru.diatonicscale.worknotes.model;

import java.time.LocalDateTime;
import java.util.List;

public class Note {
    private String name;
    private List<Link> sources;
    private boolean importance = false;
    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    private Content value;
}
