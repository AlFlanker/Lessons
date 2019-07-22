package com.gmail.alexflanker89.Lesson_12.services.interfaces;

import com.gmail.alexflanker89.Lesson_12.domain.Author;
import com.gmail.alexflanker89.Lesson_12.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author save(AuthorDTO authorDTO);
}
