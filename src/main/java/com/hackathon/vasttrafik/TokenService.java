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
public class TokenService {

    private String key = "Basic TzRCWEUwVDMwYk10R3hQbUM4WjVmdGF0NmZNYTpLUm5CNUVwajZxVzlzaXFGdGZ4UEdLVUh0UW9h";

    private Gson gson = new Gson();

    public Token getToken () throws Exception {
        Token token = null;
        String url = "https://api.vasttrafik.se:443/token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", key);
        HttpEntity<String> entity = new HttpEntity<String>("grant_type=client_credentials", headers);

        String result = restTemplate.postForObject(url, entity, String.class);

        token = gson.fromJson(result, Token.class);
        return token;

    }

}
