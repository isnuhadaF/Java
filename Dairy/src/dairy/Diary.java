package dairy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Diary {
    private final String username;
    private final String password;
    public boolean locked;
    private final List<Entry> entries = new ArrayList<>();

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void unlock(String password) {
        locked = false;
    }


    public void lock() {
        locked = true;
    }

    public boolean isLocked() {
        return locked;
    }

    public void check(boolean isLocked) {
        if (isLocked) throw new IllegalArgumentException("LOCKED");
    }

    //Create Entry
    public void create(String title, String body) {
        check(isLocked());
    }

    //delete entry
    public void delete(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) entries.remove(entry); break;
        }
    }

    //Find entry by id
    public Entry find(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) return entry; break;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void update(int id, String title, String body) {
        entries.add(new Entry(id, title, body));
    }


}
