package ru.itis.flaremarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flaremarket.models.ProfileImage;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.models.UserSold;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSoldsDto {
    private Long buyerId;
    private Time time;
    private Date date;

    public static UserSoldsDto from(UserSold userSold) {
        return UserSoldsDto.builder()
                .buyerId(userSold.getBuyerId())
                .time(userSold.getTime())
                .date(userSold.getDate())
                .build();
    }

    public static List<UserSoldsDto> from(List<UserSold> users) {
        return users.stream()
                .map(UserSoldsDto::from)
                .collect(Collectors.toList());
    }
}
