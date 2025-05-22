package de.ait.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class PostDto {
    private int userId;
    private int id;
    private String title;
    private String body;
}
