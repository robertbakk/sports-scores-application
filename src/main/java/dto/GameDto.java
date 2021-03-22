package dto;

public class GameDto {

    private String id;

    private String team1;

    private String team2;

    private int scoreTeam1;

    private int scoreTeam2;

    private String status;

    private String category;




    public GameDto(String id, String team1, String team2, String category) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.scoreTeam1 = 0;
        this.scoreTeam2 = 0;
        this.category = category;
        this.status = "Not started";
    }

    public GameDto() {}


    public String getId() {
        return id;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        String s = "Details about the game " + team1 + " - " + team2 + "\n";
        s += "The category of the game is " + category;
        if (status.equals("Not started"))
            s+="\nThe game hasn't started yet.";
        if (status.equals("Started"))
            s+="\nThe game is in progress. The score is " + scoreTeam1 + "-" + scoreTeam2;
        if (status.equals("Ended"))
            s+="\nThe game ended. The final score was " + scoreTeam1 + "-" + scoreTeam2;
        return s;
    }
}
