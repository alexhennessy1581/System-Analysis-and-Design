package bananas.bananas_project_1;

/**
 * Created by thejacko42 on 5/2/2017.
 */

public class Banana {
    private int id;
    private String color;
    private String date;
    private String time;
    private String name;

    public Banana(String newDate, String newTime, String newColor, String newName) {
        this.date = newDate;
        this.time = newTime;
        this.color = newColor;
        this.name = newName;
    }

    public Banana(int newId, String newDate, String newTime, String newColor, String newName) {
        this.id = newId;
        this.date = newDate;
        this.time = newTime;
        this.color = newColor;
        this.name = newName;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String newTime) {
        this.time = newTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() { return this.color; }

    public void setColor (String newColor) { this.color = newColor; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
