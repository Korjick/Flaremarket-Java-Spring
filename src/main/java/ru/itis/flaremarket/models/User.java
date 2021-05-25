package ru.itis.flaremarket.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flaremarket.models.enums.user.UserRole;
import ru.itis.flaremarket.models.enums.user.UserState;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private Boolean oauthLinked;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profileImageId", referencedColumnName = "id")
    private ProfileImage profileImage;

    @Enumerated(value = EnumType.STRING)
    private UserState state;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<FileInfo> files;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<SellItem> sellItems;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<UserSold> solds;

    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    Set<Tag> userTags;

    private String confirmCode;
    private UserRole role;
}
