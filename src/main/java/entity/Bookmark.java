package entity;

import javax.persistence.*;

@Entity
@Table(name = "bookmark")
public class Bookmark {

    @Id
    private String id;

    @Column
    private String userID;

    @Column
    private String gameID;



    public Bookmark(String id, String userID, String gameID) {
        this.id = id;
        this.userID = userID;
        this.gameID = gameID;
    }

    public Bookmark() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
}
