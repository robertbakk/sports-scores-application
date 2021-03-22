package dto;

public class BookmarkDto {
    private String id;

    private String userID;

    private String gameID;



    public BookmarkDto(String id, String userID, String gameID) {
        this.id = id;
        this.userID = userID;
        this.gameID = gameID;
    }

    public BookmarkDto() {}

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
