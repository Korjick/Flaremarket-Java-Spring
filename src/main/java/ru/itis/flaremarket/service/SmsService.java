package ru.itis.flaremarket.service;

public interface SmsService {
    void sendSms(String phone, String text);
}
