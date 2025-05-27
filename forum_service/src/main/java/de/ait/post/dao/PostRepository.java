package de.ait.post.dao;

import de.ait.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public interface PostRepository extends MongoRepository<Post, String> {

    Stream<Post> findByAuthorIgnoreCase(String author);

    Stream<Post> findPostsByTagsInIgnoreCase(List<String> tag);
    List<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);

}


