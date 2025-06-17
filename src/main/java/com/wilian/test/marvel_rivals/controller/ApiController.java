package com.wilian.test.marvel_rivals.controller;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import com.wilian.test.marvel_rivals.models.mySql.User;
import com.wilian.test.marvel_rivals.service.HeroeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
        if (user.getId()==null || user.getId()<0){
            if ((user.getPassword()==null || user.getPassword().isEmpty()) ||
                    (user.getEmail()==null||user.getEmail().isEmpty())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }else {
                Optional<User> user1 = service.findUser(user.getPassword(), user.getEmail());
                if (user1.isPresent()){
                    return ResponseEntity.ok(user1.get());
                }else {
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
                if (Objects.equals(user1.get().getNombre(),"Ya existe el nombre que colocaste, utiliza otro porfavor")){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user1.get());
                }
                if (Objects.equals(user1.get().getEmail(),("Ya existe el email que colocaste, utiliza otro porfavor"))) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user1.get());
                }
                return ResponseEntity.ok(user1.get());

            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }

    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        Optional<User> user = service.deletedUser(id);
        if (user.isPresent()){
            return ResponseEntity.ok("Usuario Eliminado Correctamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
    }

    @PostMapping("/recover-to-user")
    public ResponseEntity<User> RecoverUser(@RequestBody User user){
        if (user!=null && !user.getNombre().isEmpty() && !user.getEmail().isEmpty()){
            Optional<User> user1 = service.recoverUser(user.getNombre(), user.getEmail());
            if (user1.isPresent()){
                return ResponseEntity.ok(user1.get());
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/find-user/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id")Integer id){
        Optional<User> user = service.findUser(id);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //METHODS API HERO
    @GetMapping("/findAll")
    public ResponseEntity<List<Heroe>> findAll(){
        List<Heroe> heroes = service.HerofindAll();
        if (heroes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            System.out.println();
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
