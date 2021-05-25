package ru.itis.flaremarket.service;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.flaremarket.dto.UserSoldsDto;
import ru.itis.flaremarket.models.UserSold;
import ru.itis.flaremarket.repository.UserSoldRepository;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserSoldServiceImpl implements UserSoldService {

    @Autowired
    private UserSoldRepository userSoldRepository;

    @Override
    public void addUserSold(Long userId, Long buyerId) {
        userSoldRepository.save(UserSold
                .builder()
                .date(new Date(System.currentTimeMillis()))
                .userId(userId)
                .buyerId(buyerId)
                .time(new Time(System.currentTimeMillis()))
                .build());
    }

    @Override
    public List<UserSoldsDto> getAllSolds(Long userId) {
        return UserSoldsDto.from(userSoldRepository.findAllByUserId(userId));
    }

    @Override
    public List<Pair<String, Long>> getLastWeekSolds(Long userId) {
         Map<Date, Long> map =
                 userSoldRepository
                         .getLastWeekSolds(userId)
                         .stream()
                         .collect(Collectors.groupingBy(UserSold::getDate, Collectors.counting()));


         List<Pair<String, Long>> pairList = new ArrayList<>();
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");

         map.forEach((key, value) -> pairList.add(new Pair<>(simpleDateFormat.format(key), value)));
         pairList.sort(Comparator.comparing(Pair::getKey));
         return pairList;
    }
}
