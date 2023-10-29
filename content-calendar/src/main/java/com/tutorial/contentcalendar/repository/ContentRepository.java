package com.tutorial.contentcalendar.repository;

import com.tutorial.contentcalendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {
    

}
