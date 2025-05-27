package de.ait.post.dao;

import com.mongodb.client.model.Collation;
import de.ait.post.dto.PostDto;
import de.ait.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public interface PostRepository extends MongoRepository<Post, String> {

    Stream<PostDto> findByAuthorIgnoreCase(String author);
    Post findByIdAndAuthorIgnoreCase(String id, String author);
    Stream<PostDto> findByTagsIn(Collection<String> tags);
    List<Post> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}


