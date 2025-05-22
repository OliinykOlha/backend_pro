package de.ait.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class TagsDto {
    private double confidence;
    private Map<String, String> tag;
}
