package com.gmail.alexflanker89.Lesson_10;

import com.gmail.alexflanker89.Lesson_10.domain.Book;
import com.gmail.alexflanker89.Lesson_10.domain.Comment;
import com.gmail.alexflanker89.Lesson_10.services.interfaces.BookService;
import com.gmail.alexflanker89.Lesson_10.services.interfaces.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@DisplayName("Проверка сервеса комментариев")
@ExtendWith(SpringExtension.class)
public class CommentServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @DisplayName("Добавить комментарий")
    public void addCommitTest() {
        Comment comment = new Comment();
        comment.setUsername("testUser");
        comment.setComment("Something very long text");
        comment.setCreated(LocalDateTime.now());
        Book book = new Book();
        book.setTitle("test comment");
        Book save = bookService.save(book);
        commentService.addComment(save.getId(), comment);
        List<Book> list = mongoOperations.findAllAndRemove(new Query().addCriteria(Criteria.where("title").is("test comment")), Book.class);
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    @DisplayName("Получить комментарии по id книги")
    public void getAllCommentByBookIdTest(){
        Book book = bookService.getAll().get(0);
        List<Comment> comments = commentService.getAllCommentByBookId(book.getId());
        Assertions.assertTrue(comments.size()>0);
    }
}
