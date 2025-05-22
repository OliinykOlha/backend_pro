package de.ait;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.dto.PostDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PostApi {
    private static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws URISyntaxException, IOException {

        RestTemplate restTemplate = new RestTemplate();


        URI uri = new URI("https://jsonplaceholder.typicode.com/posts?userId=5");


        RequestEntity<String> request = new RequestEntity<>(HttpMethod.GET, uri);

        ResponseEntity<List<PostDto>>  response = restTemplate.exchange(request, new ParameterizedTypeReference<List<PostDto>>() {
        });

        System.out.println(response.getHeaders().get("Date"));
        System.out.println(response.getStatusCode());

        List<PostDto> posts = response.getBody();
        posts.forEach(System.out::println);

         mapper.writeValue(new File("posts.json"), posts);






    }
}
