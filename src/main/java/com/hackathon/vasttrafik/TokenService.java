package com.hackathon.vasttrafik;


import com.google.gson.Gson;
import com.hackathon.vasttrafik.models.Token;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
@RestController
@RequestMapping("/api/")
public class TokenService {

    String key = "Basic eVFteXNBQ1hDbWlQWlFBc0FaRWxKSEJFOEE4YTpMSlZ3cXdtMHdab21yeVZXdkN4ZHA1dFE3TElh";

    Gson gson = new Gson();

    @RequestMapping(value="/token", method= RequestMethod.GET)
    public Token getToken () throws Exception {

        String url = "https://api.vasttrafik.se:443/token";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", key);

        HttpEntity<String> entity = new HttpEntity<String>("grant_type=client_credentials", headers);

        String result = restTemplate.postForObject(url, entity, String.class);

        Token token = gson.fromJson(result, Token.class);

        return token;

    }

}
