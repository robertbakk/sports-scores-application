package entity;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    private String id;

    @Column
    private String team1;

    @Column
    private String team2;

    @Column
    private int scoreTeam1;

    @Column
    private int scoreTeam2;

    @Column
    private String status;

    @Column
    private String category;



    public Game(String id, String team1, String team2, String category) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.scoreTeam1 = 0;
        this.scoreTeam2 = 0;
        this.category = category;
        this.status = "Not started";
    }

    public Game() {}


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
}
