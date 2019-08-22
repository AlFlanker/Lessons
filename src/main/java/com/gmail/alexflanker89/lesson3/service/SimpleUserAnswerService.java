package com.gmail.alexflanker89.lesson3.service;

import com.gmail.alexflanker89.lesson3.dao.UserAnswerDao;
import com.gmail.alexflanker89.lesson3.domain.User;
import com.gmail.alexflanker89.lesson3.domain.UserAnswer;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class SimpleUserAnswerService implements UserAnswerService {
    private UserAnswerDao userAnswerDao;

    public SimpleUserAnswerService(UserAnswerDao userAnswerDao) {
        this.userAnswerDao = userAnswerDao;
    }

    @Override
    public void reqAnswer(UserAnswer answer) {
        if ((answer.getUser() != null && answer.getQuestion() != null)) {
            userAnswerDao.save(answer);
        }
    }

    @Override
    public List<UserAnswer> getAllByUser(User user) {
        if (user != null) {
            return userAnswerDao.findByUser(user);
        } else {
            return Collections.emptyList();
        }
    }
}
