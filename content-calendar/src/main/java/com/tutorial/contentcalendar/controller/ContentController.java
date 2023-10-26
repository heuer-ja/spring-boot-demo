package com.tutorial.contentcalendar.controller;


import com.tutorial.contentcalendar.model.Content;
import com.tutorial.contentcalendar.repository.ContentCollectionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @GetMapping("")
    public List<Content> findAll(){
        return repository.findAll();
    }



}
