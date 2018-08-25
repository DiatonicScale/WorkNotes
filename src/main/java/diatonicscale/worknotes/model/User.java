package diatonicscale.worknotes.model;

import java.util.Date;
import java.util.Set;

public class User {
    private int id;

    private String name;
    private String password;
    private String email;

    private Date registered; // тип данных?
    protected Set<Role> roles;

    public int getId() {
        return id;
    }
}
