package service;

import dto.BookmarkDto;
import dto.GameDto;
import dto.UserDto;
import entity.Bookmark;
import mappers.BookmarkMapper;
import repository.BookmarkRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookmarkService {
    private final static Logger log = Logger.getLogger(BookmarkService.class.getName());


    private BookmarkRepo bookmarkRepo;

    public BookmarkService(BookmarkRepo bookmarkRepo) {
        log.info("Game service initialised");
        this.bookmarkRepo = bookmarkRepo;
    }

    public boolean bookmarkExists(String userID, String gameID) {
        Bookmark bookmark = bookmarkRepo.findBookmark(userID, gameID);
        return bookmark != null;
    }

    public void addBookmark(BookmarkDto bookmarkDto) {
        if(bookmarkDto==null) {
            log.warning("Invalid bookmark");
            log.severe("NULL BOOKMARK");
        }


        log.info("Successfully added a new bookmark.");
        log.info(bookmarkDto.toString());

        Bookmark bookmark = new Bookmark();
        bookmark.setId(bookmarkDto.getId());;
        bookmark.setUserID(bookmarkDto.getUserID());
        bookmark.setGameID(bookmarkDto.getGameID());

        bookmarkRepo.insertNewBookmark(bookmark);
    }

    public void removeBookmark(String userID, GameDto gameDto) {
        log.info("Successfully deleted a bookmark.");

        bookmarkRepo.removeBookmark(userID, gameDto.getId());
    }

    public void removeBookmarks(GameDto gameDto) {
        log.info("Successfully deleted bookmarks.");

        bookmarkRepo.removeBookmarks(gameDto.getId());
    }

    public ArrayList<BookmarkDto> getBookmarkedGames(UserDto userDto) {

        List<Bookmark> bookmarks = bookmarkRepo.getBookmarkedGames(userDto);
        ArrayList<BookmarkDto> bookmarksDto = new ArrayList<BookmarkDto>();
        for (Bookmark b : bookmarks) {
            BookmarkDto bookmarkDto = BookmarkMapper.entityToDto(b);
            bookmarksDto.add(bookmarkDto);
        }
        return bookmarksDto;
    }

    public ArrayList<String> getUserIDs(GameDto gameDto) {
        ArrayList<String> userIDs = (ArrayList) bookmarkRepo.getUserIDs(gameDto);
        return userIDs;
    }
}
