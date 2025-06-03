package com.wilian.test.marvel_rivals.controller;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import com.wilian.test.marvel_rivals.models.mySql.User;
import com.wilian.test.marvel_rivals.service.HeroeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ApiController {

    @Autowired
    private HeroeService service;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    //METHODS API USER

    @PostMapping("/find-user")
    public ResponseEntity<User> Users(@RequestBody User user){
        System.out.println("Solicitud recibida en buscarUser: " + user.getEmail() + " | " + user.getPassword());
        if (user.getId()==null || user.getId()<0){
            if ((user.getPassword()==null || user.getPassword().isEmpty()) ||
                    (user.getEmail()==null||user.getEmail().isEmpty())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }else {
                Optional<User> user1 = service.findUser(user.getPassword(), user.getEmail());
                if (user1.isPresent()){
                    System.out.println("Entro aqui 1");
                    return ResponseEntity.ok(user1.get());
                }else {
                    System.out.println("Entro aqui 2");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/register-user")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        if ((user.getPassword()==null || user.getPassword().isEmpty()) ||
                (user.getEmail()==null||user.getEmail().isEmpty()) ||
                (user.getNombre()==null || user.getNombre().isEmpty())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else{
            Optional<User> user1 = service.registerUser(user.getNombre(), user.getPassword(), user.getEmail());
            if (user1.isPresent()){
                System.out.println(user1.get().getNombre() + " || " + user1.get().getEmail() + " || el usuario del services lo que esta devolviendo");
                if (Objects.equals(user1.get().getNombre(),"Ya existe el nombre que colocaste, utiliza otro porfavor")){
                     System.out.println("Cae aqui nombre igual");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user1.get());
                }
                if (Objects.equals(user1.get().getEmail(),("Ya existe el email que colocaste, utiliza otro porfavor"))) {
                     System.out.println("Cae aqui email igual");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user1.get());
                }
                return ResponseEntity.ok(user1.get());

            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }

    }

    //METHODS API HERO
    @GetMapping("/findAll")
    public ResponseEntity<List<Heroe>> findAll(){
        List<Heroe> heroes = service.HerofindAll();
        if (heroes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(heroes);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Heroe> findHero(@PathVariable Integer id){
        return ResponseEntity.ok(service.findHeroId(id).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Heroe> editHero(@RequestBody Heroe heroe, @PathVariable Integer id){
        return ResponseEntity.ok(service.editHero(id,heroe).get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String > deletedHero(@PathVariable Integer id){
        return ResponseEntity.ok(service.deletedHero(id).get());
    }
    @PutMapping("/recuperar/{id}")
    public ResponseEntity<Heroe> recoveryHero(@PathVariable Integer id){
        return ResponseEntity.ok(service.recoveryHero(id).get());
    }

    //METHODS GOOGLE OAUTH2.0
    @GetMapping("/gmail/messages")
    public String listMessages(Authentication authentication){
        if (authentication instanceof OAuth2AuthenticationToken token){
            OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                    token.getAuthorizedClientRegistrationId(),
                    token.getName()
            );
            if (client!=null){
                String Acces_token=client.getAccessToken().getTokenValue();

                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(Acces_token);

                ResponseEntity<String > response = new RestTemplate().exchange(
                        "https://gmail.googleapis.com/gmail/v1/users/me/messages?maxResults=5",
                        HttpMethod.GET,new HttpEntity<>(headers),String.class
                        );
                return response.getBody();
            }
        }
        return "NO SE AUTENTICO";
    }

    @GetMapping("/login/google/redirect")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }
}
