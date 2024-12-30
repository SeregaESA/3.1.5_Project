import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Consumer {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://94.198.50.185:7081/api/users";

        //Cookies
        RestTemplate template = new RestTemplate();
        ResponseEntity forEntity = template.getForEntity(url, String.class);
        String cookies = forEntity.getHeaders().getFirst("Set-Cookie");
        System.out.println(cookies);

        //Post
        User userPost = new User((long) 3, "James", "Brown", (byte) 30);

        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        headersPost.set("Cookie", cookies);
        HttpEntity<User> entityPost = new HttpEntity<>(userPost, headersPost);
        ResponseEntity responsePost = restTemplate.exchange(url, HttpMethod.POST, entityPost, String.class);
        System.out.println(responsePost.getBody());

        //Put
        User userPut = new User((long) 3, "Thomas", "Shelby", (byte) 30);

        HttpHeaders headersPut = new HttpHeaders();
        headersPut.setContentType(MediaType.APPLICATION_JSON);
        headersPut.set("Cookie", cookies);
        HttpEntity<User> entity = new HttpEntity<>(userPut, headersPut);
        ResponseEntity responsePut = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        System.out.println(responsePut.getBody());

        //Delete
        HttpHeaders headersDelete = new HttpHeaders();
        headersDelete.setContentType(MediaType.APPLICATION_JSON);
        headersDelete.set("Cookie", cookies);
        HttpEntity<String> entityDelete = new HttpEntity<>(headersDelete);
        ResponseEntity responseDelete = restTemplate.exchange(url + "/" + "3", HttpMethod.DELETE, entityDelete, String.class);
        System.out.println(responseDelete.getBody());


    }
}
