package de.ait.security;

import de.ait.post.dao.PostRepository;
import de.ait.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomWebSecurity {
    private final PostRepository postRepository;

    public boolean checkPostAuthor(String postId, String userName) {
        Post post = postRepository.findById(postId).orElseThrow(null);
        return post != null && post.getAuthor().equalsIgnoreCase(userName);
    }
}
