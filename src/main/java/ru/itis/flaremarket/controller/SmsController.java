package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.flaremarket.models.forms.sms.SmsForm;
import ru.itis.flaremarket.service.SmsService;

@Controller
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/sms")
    @ResponseBody
    public void sendSms(@RequestBody SmsForm smsForm){
        smsService.sendSms(smsForm.getPhone(), smsForm.getText());
    }
}
