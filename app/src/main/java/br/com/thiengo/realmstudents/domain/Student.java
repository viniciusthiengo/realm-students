package br.com.thiengo.realmstudents.domain;

import java.util.List;

/**
 * Created by viniciusthiengo on 11/2/15.
 */
public class Student {
    public static final String ID = "br.com.thiengo.realmexample.domain.Student.ID";

    private long id;
    private String name;
    private String email;
    private List<Discipline> disciplines;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}
