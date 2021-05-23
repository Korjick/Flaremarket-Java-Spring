package ru.itis.flaremarket.models.forms.sms;

import lombok.Data;

@Data
public class SmsForm {
    String phone;
    String text;
}
