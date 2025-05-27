package de.ait.post.service;

import de.ait.post.dto.NewCommentDto;
import de.ait.post.dto.NewPostDto;
import de.ait.post.dto.PostDto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface PostService {
    PostDto addPost(String author, NewPostDto newPostDto);
    PostDto findPostById(String id);
    void addLike(String id);
    Iterable<PostDto> findPostsByAuthor(String author);
    PostDto addComment(String id, String author, NewCommentDto newCommentDto);
    PostDto deletePost(String id);
    Iterable<PostDto> findPostsByTag(List<String> tags);
    Iterable<PostDto> findPostsByPeriod(LocalDate localDateFrom, LocalDate localDateTo);
    PostDto updatePost(String id, NewPostDto newPostDto);


}
