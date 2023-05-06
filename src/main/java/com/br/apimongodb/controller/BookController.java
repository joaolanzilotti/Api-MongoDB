package com.br.apimongodb.controller;

import com.br.apimongodb.models.Book;
import com.br.apimongodb.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok().body(bookService.getAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable String id){
        return ResponseEntity.ok().body(bookService.getById(id));
    }

    @RequestMapping(path = "/books/{criteria}/{search}", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getByCriteria(@PathVariable String criteria, @PathVariable String search){
        return ResponseEntity.ok().body(bookService.getByCriteria(criteria,search));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> create(@RequestBody Book book){
        return ResponseEntity.ok().body(bookService.create(book));
    }

    @PutMapping("/books")
    public ResponseEntity<Boolean> update(@RequestBody Book book){
        return ResponseEntity.ok().body(bookService.update(book));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        return ResponseEntity.ok().body(bookService.delete(id));
    }

}
