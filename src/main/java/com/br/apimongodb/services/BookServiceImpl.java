package com.br.apimongodb.services;

import com.br.apimongodb.models.Book;
import com.br.apimongodb.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Boolean delete(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            bookRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getByCriteria(String criteria, String search) {
        Query query = new Query();
        query.addCriteria(Criteria.where(criteria).regex(String.format(".*%S", search), "i"));
        List<Book> books = mongoTemplate.find(query, Book.class);
        return books;
    }

    @Override
    public Book getById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        return null;
    }

    public Boolean update(Book book) {
        Optional<Book> op = bookRepository.findById(book.getId());
        if(op.isPresent()){
            bookRepository.save(book);
            return true;
        }
        return null;
    }
}
