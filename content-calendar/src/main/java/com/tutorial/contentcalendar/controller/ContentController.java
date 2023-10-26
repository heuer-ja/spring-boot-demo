package com.tutorial.contentcalendar.controller;


import com.tutorial.contentcalendar.model.Content;
import com.tutorial.contentcalendar.repository.ContentCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


// Controller for CRUD Content
@RestController
@RequestMapping("/api/content") // path to get to the controller
public class ContentController {

    private final ContentCollectionRepository repository;
    public ContentController(
        // Dependency injection
        ContentCollectionRepository repository
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


}
