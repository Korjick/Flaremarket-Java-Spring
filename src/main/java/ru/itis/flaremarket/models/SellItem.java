package ru.itis.flaremarket.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flaremarket.models.enums.sellitem.Game;
import ru.itis.flaremarket.models.enums.sellitem.SellType;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sellItems")
public class SellItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String image;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private Game game;
    @Enumerated(value = EnumType.STRING)
    private SellType sellType;

    private String platform;
    private Double price;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "betId", referencedColumnName = "id")
    private Bet bet;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sellItemId", referencedColumnName = "id")
    private List<SellItemPrice> priceList;
}
