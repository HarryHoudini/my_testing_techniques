package main.java.data;

import lombok.Getter;

@Getter
public class Credential {
    private final String caption;
    private final String username;
    private final String password;

    public Credential(String caption, String username, String password) {
        this.caption = caption;
        this.username = username;
        this.password = password;
    }

    public static Credential credential(String caption, String login, String password){
        return new Credential(caption, login, password);
    }
}
