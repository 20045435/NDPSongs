package sg.edu.rp.c346.id20045435.ndpsongs;

import java.io.Serializable;

public class Song implements Serializable {
    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public Song(int _id, String title, String singers, int year, int stars) {
        this._id = _id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    public void setSongContent(String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public String toString() {
        String starString = "";

        if (stars == 1) {
            starString = "*";
        }
        else if (stars == 2) {
            starString = "**";
        }
        else if (stars == 3) {
            starString = "***";
        }
        else if (stars == 4) {
            starString = "****";
        }
        else if (stars == 5) {
            starString = "*****";
        }
        return title + "\n" + singers + " - " + year + "\n" + starString;
    }
}
