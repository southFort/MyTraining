package w2d1;

import java.io.Serializable;

public class Courier {
    private String name;
    private int id;

    public Courier() {
    }

    public Courier(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
