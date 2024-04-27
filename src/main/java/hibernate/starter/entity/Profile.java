package hibernate.starter.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "profile", schema = "public")
public class Profile {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    private String street;
    private String language;

    public void setUser(User user){
        this.user = user;
//        user.setProfile(this);
        userId = user.getId();
    }
}
