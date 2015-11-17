package br.com.thiengo.realmstudents.domain;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by viniciusthiengo on 11/2/15.
 */
public class Student extends RealmObject {
    public static final String ID = "br.com.thiengo.realmexample.domain.Student.ID";

    @PrimaryKey
    private long id;
    private String name;
    private String email;
    private RealmList<Grade> grades;
    private Address address;
    private RealmList<Student> friends;


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

    public RealmList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(RealmList<Grade> grades) {
        this.grades = grades;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RealmList<Student> getFriends() {
        return friends;
    }

    public void setFriends(RealmList<Student> friends) {
        this.friends = friends;
    }
}
