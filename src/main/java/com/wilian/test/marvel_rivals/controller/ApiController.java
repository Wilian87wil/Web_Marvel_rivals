package com.wilian.test.marvel_rivals.controller;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import com.wilian.test.marvel_rivals.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiController {
    @Autowired
    private HeroeService service;

   private final OAuth2AuthorizedClientService authorizedClientService;

    public ApiController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Heroe> buscarHeroe(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPorId(id).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Heroe> editar(@RequestBody Heroe heroe, @PathVariable Integer id){
        return ResponseEntity.ok(service.Editar(id,heroe).get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String > deleted(@PathVariable Integer id){
        return ResponseEntity.ok(service.eliminar(id).get());
    }
    @PutMapping("/recuperar/{id}")
    public ResponseEntity<Heroe> recuprar(@PathVariable Integer id){
        return ResponseEntity.ok(service.recuperarId(id).get());
    }
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
}
