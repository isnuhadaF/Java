package dairy;

import java.util.List;

public class Diaries {
    private List<Diary> diaries;


    public void add(String username, String password) {

    }

    public Diary find(String username) {
        for (Diary diary : diaries) {
            if (username.equals(diary.getUsername())) return diary; break;
        }
        return null;
    }

    public void delete(String username, String password) {

    }
}
