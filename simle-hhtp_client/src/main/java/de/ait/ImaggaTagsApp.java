package de.ait;

import com.sun.net.httpserver.Headers;
import de.ait.dto.TagsResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.HTML;
import java.net.URI;
import java.net.URISyntaxException;

public class ImaggaTagsApp {
    public static void main(String[] args) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String lang = "de";
        int threshold = 30;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic YWNjXzlkNTY0MzVkMjllM2U4MzowOGIxODc3Yzg5ZGZhNDk1ZDgzNmY4N2EyYTkyNTVkOQ==");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://api.imagga.com/v2/tags")
                .queryParam("image_url", "https://docs.imagga.com/static/images/docs/sample/japan-605234_1280.jpg")
                .queryParam("language", lang)
                .queryParam("threshold", threshold);
        URI uri = builder.build().toUri();
        RequestEntity<String> request = new RequestEntity<>(headers,HttpMethod.GET, uri );
        ResponseEntity<TagsResponseDto> response = restTemplate.exchange(request, TagsResponseDto.class);
        response.getBody().getResult().getTags().forEach(System.out::println);
    }
}
