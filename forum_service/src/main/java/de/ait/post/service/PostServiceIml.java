package de.ait.post.service;

import de.ait.post.dao.PostRepository;
import de.ait.post.dto.NewCommentDto;
import de.ait.post.dto.NewPostDto;
import de.ait.post.dto.PostDto;
import de.ait.post.dto.exception.PostNotFoundException;
import de.ait.post.model.Comment;
import de.ait.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceIml implements PostService {

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    @Override
    public PostDto addPost(String author, NewPostDto newPostDto) {
        Post post = modelMapper.map(newPostDto, Post.class);
        post.setAuthor(author);
        post = postRepository.save(post);
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.addLike();
        post = postRepository.save(post);

    }

    @Override
    public Iterable<PostDto> findPostsByAuthor(String author) {
        return postRepository.findByAuthorIgnoreCase(author)
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    public PostDto addComment(String id, String author, NewCommentDto newCommentDto) {
        Post post = postRepository.findByIdAndAuthorIgnoreCase(id, author);
        if(post == null) {
            throw new PostNotFoundException();

        }
        Comment comment = modelMapper.map(newCommentDto, Comment.class);
        post.addComment(comment);
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto deletePost(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public Iterable<PostDto> findPostsByTag(List<String> tags) {
        return postRepository.findByTagsIn(tags)
                .map(p->modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    public Iterable<PostDto> findPostsByPeriod(LocalDateTime localDateFrom, LocalDateTime localDateTo) {
        List<Post> posts = postRepository.findByDateCreatedBetween(localDateFrom, localDateTo);

        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public PostDto updatePost(String id, NewPostDto newPostDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.setTitle(newPostDto.getTitle());
        post.setContent(newPostDto.getContent());
        post.setTags(newPostDto.getTags());
        Post newPost = postRepository.save(post);
       return modelMapper.map(newPost, PostDto.class);

    }

        }
