package repository;

import entity.Game;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


public class GameRepo {

    private static String DELETE_GAME_QUERY = "DELETE from Game where team1 = :team1 and team2 = :team2";
    private static String UPDATE_GAME_QUERY = "UPDATE Game a set a.team1 = :updatedTeam1, a.team2 = :updatedTeam2, a.scoreTeam1 = :updatedScore1, a.scoreTeam2 = :updatedScore2, a.category = :updatedCategory, a.status = :updatedStatus WHERE a.team1 = :team1 and a.team2 = :team2";
    private static String GET_GAMES_QUERY = "SELECT a from Game a";
    private static String GET_GAME_QUERY = "SELECT a from Game a where a.team1 = :team1 and a.team2 = :team2";
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore");


    public void insertNewGame(Game game) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(game);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteGame(String team1, String team2) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery(DELETE_GAME_QUERY);
        q.setParameter("team1", team1);
        q.setParameter("team2", team2);
        q.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public Game findGame(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Game game =	em.find(Game.class,id);
        em.close();
        return game;
    }

    public List<Game> getGames() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(GET_GAMES_QUERY, Game.class);
        List<Game> games = jpqlQuery.getResultList();
        em.close();
        return games;
    }

    public Game getGame(String team1, String team2) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery(GET_GAME_QUERY);
        q.setParameter("team1", team1);
        q.setParameter("team2", team2);
        Game game = (Game) q.getResultList().get(0);
        em.close();
        return game;
    }

    public void updateGame(String team1, String team2, String updatedTeam1, String updatedTeam2, String updatedScore1, String updatedScore2, String updatedCategory, String updatedStatus) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery(UPDATE_GAME_QUERY);
        q.setParameter("team1", team1);
        q.setParameter("team2", team2);
        q.setParameter("updatedTeam1", updatedTeam1);
        q.setParameter("updatedTeam2", updatedTeam2);
        q.setParameter("updatedScore1", Integer.parseInt(updatedScore1));
        q.setParameter("updatedScore2", Integer.parseInt(updatedScore2));
        q.setParameter("updatedCategory", updatedCategory);
        q.setParameter("updatedStatus", updatedStatus);
        q.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

}
