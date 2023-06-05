package pl.orange.NextDoorBook.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final ICommentRepository iCommentRepository;

    public Comment addComment(Comment comment) {
        comment.setAddTime(LocalDateTime.now());
        return iCommentRepository.save(comment);
    }

    public void deleteCommentByID(Long id) {
        iCommentRepository.deleteById(id);
    }

    public Optional<Comment> getCommentByID(Long id) {
        return iCommentRepository.findById(id);
    }

    public Set<Comment> getCommentsByBookID(Long id) {
        return iCommentRepository.findCommentsByBookId(id);
    }

    public void updateComment(Long id, String message, boolean spoilerAlert, Book book, User user, int rate) {
        iCommentRepository.updateComment(id, message, spoilerAlert, LocalDateTime.now(), book, user, rate);
    }
    public Double averageBookRate(Book book){
        return iCommentRepository.averageBookRate(book);
    }

}
