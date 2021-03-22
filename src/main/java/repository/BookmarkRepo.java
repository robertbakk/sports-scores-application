package repository;

import dto.GameDto;
import dto.UserDto;
import entity.Bookmark;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class BookmarkRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore");
    private static String BOOKMARK_QUERY = "SELECT a from Bookmark a where a.userID = :userID and a.gameID = :gameID";
    private static String GET_BOOKMARKED_GAMES = "SELECT a from Bookmark a where a.userID = :userID";
    private static String GET_USERIDS = "SELECT a from Bookmark a where a.gameID = :gameID";
    private static String REMOVE_BOOKMARK_QUERY = "DELETE from Bookmark where userID = :userID and gameID = :gameID";
    private static String REMOVE_BOOKMARKS_QUERY = "DELETE from Bookmark where gameID = :gameID";



    public void insertNewBookmark(Bookmark bookmark) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(bookmark);
        em.getTransaction().commit();
        em.close();
    }

    public Bookmark findBookmark(String userID, String gameID) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery(BOOKMARK_QUERY);
        q.setParameter("userID", userID);
        q.setParameter("gameID", gameID);
        if (q.getResultList().isEmpty()){
            em.close();
            return null;
        }
        Bookmark bookmark = (Bookmark) q.getResultList().get(0);
        em.close();
        return bookmark;
    }

    public List<Bookmark> getBookmarkedGames(UserDto userDto) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery(GET_BOOKMARKED_GAMES, Bookmark.class).setParameter("userID", userDto.getId());
        List<Bookmark> bookmarks = q.getResultList();
        em.close();
        return bookmarks;
    }

    public List<String> getUserIDs(GameDto gameDto) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery(GET_USERIDS, Bookmark.class).setParameter("gameID", gameDto.getId());
        List<Bookmark> bookmarks = q.getResultList();
        em.close();
        List <String> userIDs = new ArrayList<>();
        for (Bookmark bookmark : bookmarks)
            userIDs.add(bookmark.getUserID());
        return userIDs;
    }

    public void removeBookmark(String userID, String gameID) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery(REMOVE_BOOKMARK_QUERY);
        q.setParameter("userID", userID);
        q.setParameter("gameID", gameID);
        q.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void removeBookmarks(String gameID) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery(REMOVE_BOOKMARKS_QUERY);
        q.setParameter("gameID", gameID);
        q.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
