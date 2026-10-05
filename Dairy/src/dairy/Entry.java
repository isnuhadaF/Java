package dairy;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

public class Entry {
    private int id;
    private String title;
    private String body;
    private LocalDateTime dateTime;

    public Entry(int id, String title, String body) {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }
}
