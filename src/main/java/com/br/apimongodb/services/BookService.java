package com.br.apimongodb.services;

import com.br.apimongodb.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    Book create(Book book);
    Boolean delete(String id);
    List<Book> getAll();
    List<Book> getByCriteria(String criteria, String search);
    Book getById(String id);

    Boolean update(Book book);

}
