package de.ait.post.controller;

import de.ait.post.dto.NewCommentDto;
import de.ait.post.dto.NewPostDto;
import de.ait.post.dto.PostDto;
import de.ait.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/forum")
public class PostController {
    private final PostService postService;

    @PostMapping("/post/{author}")
    public PostDto addPost(@PathVariable String author, @RequestBody NewPostDto newPostDto) {
        return postService.addPost(author, newPostDto);
    }

    @GetMapping("/post/{id}")
    public PostDto findPostById(@PathVariable String id) {
        return postService.findPostById(id);
    }

    @PatchMapping("/post/{id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable String id) {
        postService.addLike(id);
    }

 @GetMapping("/posts/author/{author}")
    public Iterable<PostDto> findPostsByAuthor(@PathVariable String author) {
        return postService.findPostsByAuthor(author);
    }

 @PatchMapping("/post/{id}/comment/{author}")
    public PostDto addComment(@PathVariable String id, @PathVariable String author,@RequestBody NewCommentDto newCommentDto) {
        return postService.addComment(id, author, newCommentDto);
    }

     @DeleteMapping("/post/{id}")
    public PostDto deletePost(@PathVariable String id) {
        return postService.deletePost(id);
    }

@GetMapping("/posts/tags")
    public Iterable<PostDto> findPostsByTag(@RequestParam ("values")List<String> tags) {
        return postService.findPostsByTag(tags);
    }

@GetMapping("posts/period")
    public Iterable<PostDto> findPostsByPeriod(@RequestParam ("dateFrom") LocalDate localDateFrom, @RequestParam ("dateTo") LocalDate localDateTo) {
        return postService.findPostsByPeriod(localDateFrom, localDateTo);
    }

@PatchMapping("/post/{id}")
    public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto newPostDto) {
        return postService.updatePost(id, newPostDto);
    }
}
