package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsServiceImpl implements SmsService {

    @Value("${sms.ru.url}")
    private String smsUrl;

    @Value("${sms.ru.api_id}")
    private String apiId;

    private final RestTemplate restTemplate;

    private SmsServiceImpl(){
        restTemplate = new RestTemplate();
    }

    @Override
    public void sendSms(String phone, String text) {
        String url = smsUrl + "?api_id=" + apiId + "&to=" + phone + "&msg=" + text + "&json=1";
        String result = restTemplate.getForObject(url, String.class);
    }
}
