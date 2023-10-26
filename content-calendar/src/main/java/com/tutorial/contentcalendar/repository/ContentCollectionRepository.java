package com.tutorial.contentcalendar.repository;

import com.tutorial.contentcalendar.model.Content;
import com.tutorial.contentcalendar.model.Status;
import com.tutorial.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {

    }

    /********************************/

    /**
     * Initializes list of `content`
     */
    @PostConstruct // PostConstruct calls method after `Dependency Injection` of this class, e.g., in a controller
    private void init() {
        this.contentList.add(new Content(
                1,
                "First Blog",
                "This is my first blog post! :)",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        ));
        return;
    }
    /********************************/



    public List<Content> findAll() {
        return this.contentList;
    }

    public Optional<Content> findById(Integer id){
        return this.contentList.stream()
                .filter(ci -> ci.id().equals(id))
                .findFirst();
    }

    public void save(Content content){
        boolean b = this.contentList.removeIf(c -> c.id().equals(content.id()));
        this.contentList.add(content);
    }

    public boolean existsById(Integer id){
        return this.contentList.stream().anyMatch(c -> c.id().equals(id));
    }


}
