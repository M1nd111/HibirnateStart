package hibernate.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Manager extends User {
    private String project;
    @Builder
    public Manager(Long id, String username, PersonalInfo personalInfo, Role role, Company company, Profile profile, ProfileAutoId profileAutoId, List<UsersChat> usersChats, String project) {
        super(id, username, personalInfo, role, company, profile, profileAutoId, usersChats);
        this.project = project;
    }
}
