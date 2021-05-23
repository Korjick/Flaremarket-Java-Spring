package ru.itis.flaremarket.service;

public interface MailsService {
    void sendEmailForConfirm(String email, String code);
}
