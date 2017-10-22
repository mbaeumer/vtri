package com.hackathon.vasttrafik;


import com.hackathon.vasttrafik.models.Token;
import com.hackathon.vasttrafik.models.TrafficInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/vasttrafik")
public class VasttrafikController {
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public TrafficInformation getTrafficStops() throws Exception {
        Token token = tokenService.getToken();
        int port = 8080;

        TrafficInformation trafficInformation = null;
        String url = "https://api.vasttrafik.se/bin/rest.exe/v2/location.allstops";

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = createHttpHeaders(token.getAccessToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            /*
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("msisdn", msisdn)
                    .queryParam("email", email)
                    .queryParam("clientVersion", clientVersion)
                    .queryParam("clientType", clientType)
                    .queryParam("issuerName", issuerName)
                    .queryParam("applicationName", applicationName);
                    */
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(url)
                    .queryParam("input", "stenpiren");

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.getBody());

        }
        catch (Exception eek) {
            System.out.println("** Exception: "+ eek.getMessage());
        }
        return trafficInformation;
    }

    private HttpHeaders createHttpHeaders(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", "*/*");
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }



}
