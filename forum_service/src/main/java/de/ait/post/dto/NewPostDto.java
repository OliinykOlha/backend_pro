package de.ait.post.dto;

import lombok.Getter;
import java.util.Set;


@Getter
public class NewPostDto {
    private String title;
    private String content;
    Set<String> tags;
}
