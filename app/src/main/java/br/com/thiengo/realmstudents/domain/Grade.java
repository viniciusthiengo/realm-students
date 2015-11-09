package br.com.thiengo.realmstudents.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by viniciusthiengo on 11/9/15.
 */
public class Grade extends RealmObject {
    @PrimaryKey
    private long id;
    private Discipline discipline;
    private double grade;


    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
