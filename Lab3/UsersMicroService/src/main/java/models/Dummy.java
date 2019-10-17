package models;

import java.io.Serializable;

public class Dummy implements Serializable {

    private long id;
    private String name;

    public Dummy() {

    }

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

    @Override
    public String toString() {
        return "Dummy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
