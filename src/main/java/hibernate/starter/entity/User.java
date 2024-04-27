package hibernate.starter.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "findUserByNameAndCompany", query = """
                select u from  User u
                join u.company c
                where u.role = :role
                and c.name = :company
                """)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"company", "profile", "usersChats"})

@EqualsAndHashCode(of = "username")
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Embedded
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;


    /*@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ProfileAutoId profileAutoId;*/

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UsersChat> usersChats = new ArrayList<>();

}
