package ru.itis.flaremarket.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Value("${api.key}")
    private String apiKey;

    private final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    private final RestTemplate restTemplate;

    public GameServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public List<String> getAllPlatforms() {

        List<String> games = new ArrayList<>();

        ResponseEntity<String> responseEntity = restTemplate.exchange("https://api.rawg.io/api/platforms"
                + "?key=" + apiKey,
                HttpMethod.GET, null, String.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            logger.info(responseEntity.getBody());
            JSONArray arr = new JSONObject(responseEntity.getBody()).getJSONArray("results");
            for(Object o : arr){
                games.add(((JSONObject) o).getString("name"));
            }
        }

        return games;
    }
}
