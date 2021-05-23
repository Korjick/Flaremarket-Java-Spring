package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.models.UserSold;
import ru.itis.flaremarket.repository.UserSoldRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@Service
public class UserSoldServiceImpl implements UserSoldService {

    @Autowired
    private UserSoldRepository userSoldRepository;

    @Override
    public void addUserSold(Long userId) {
        userSoldRepository.save(UserSold
                .builder()
                .date(new Date(System.currentTimeMillis()))
                .userId(userId)
                .time(new Time(System.currentTimeMillis()))
                .build());
    }
}
