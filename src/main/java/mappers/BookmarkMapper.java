package mappers;

import dto.BookmarkDto;
import entity.Bookmark;

public class BookmarkMapper {

    public static BookmarkDto entityToDto(Bookmark bookmark) {
        BookmarkDto bookmarkDto = new BookmarkDto();
        bookmarkDto.setId(bookmark.getId());
        bookmarkDto.setUserID(bookmark.getUserID());
        bookmarkDto.setGameID(bookmark.getGameID());
        return bookmarkDto;
    }

}

