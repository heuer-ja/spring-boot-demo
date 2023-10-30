package com.tutorial.contentcalendar.controller;


import com.tutorial.contentcalendar.model.Content;
import com.tutorial.contentcalendar.repository.ContentCollectionRepository;
import com.tutorial.contentcalendar.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


// Controller for CRUD Content
@RestController
@RequestMapping("/api/content") // path to get to the controller
@CrossOrigin
public class ContentController {

    private final ContentRepository repository;
    public ContentController(
        // Dependency injection
        ContentRepository repository
    ) {
        this.repository = repository;
    }


    //============ GET REQUESTS ===========
    @GetMapping("") // static link
    public List<Content> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}") // dynamic link due to @PathVariable
    public Content findById(@PathVariable Integer id){
        return this.repository.findById(id)
                // Exception in case of unknown id
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!"));
    }

    //============ POST REQUESTS ===========
    @ResponseStatus(HttpStatus.CREATED) // sends 201 code (== created) to user
    @PostMapping("") // parameter will be sent due to @RequestBody
    public void create(@RequestBody Content content){
        this.repository.save(content);
    }

    //============ PUT REQUESTS ===========

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}") // parameter will be sent due to @RequestBody
    public void update(@RequestBody  Content content, @PathVariable Integer id){
        // if content does not exist
        if(! this.repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");

        this.repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        this.repository.deleteById(id);
    }
}
