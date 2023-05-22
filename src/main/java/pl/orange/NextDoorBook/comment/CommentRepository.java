package pl.orange.NextDoorBook.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final ICommentRepository iCommentRepository;

    public void addComment(Comment comment) {
        iCommentRepository.save(comment);
    }

    public void deleteCommentByID(Long id) {
        iCommentRepository.deleteById(id);
    }

    public Optional<Comment> getCommentByID(Long id) {
        return iCommentRepository.findById(id);
    }

    public Optional<Set<Comment>> getCommentsByBookID(Long id){
        return iCommentRepository.findCommentsByBookId(id);
    }

    public void updateComment(Long id, String message, boolean spoilerAlert, Book book, User user) {
        iCommentRepository.updateComment(id, message, spoilerAlert, book, user);
    }

}
