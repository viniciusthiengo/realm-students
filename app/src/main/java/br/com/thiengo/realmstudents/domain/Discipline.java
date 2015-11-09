package br.com.thiengo.realmstudents.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by viniciusthiengo on 11/2/15.
 */
public class Discipline extends RealmObject {
    public static final String ID = "br.com.thiengo.realmstudents.domain.RealmObject.ID";

    @PrimaryKey
    private long id;
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
