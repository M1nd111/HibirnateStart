package hibernate.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_chat", schema = "public")
public class UsersChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    private Instant createdAt;
    private String createdBy;

    public void setChat(Chat chat){
        this.chat = chat;
        chat.getUsers().add(this);
    }
    public void setUser(User user){
        this.user = user;
        user.getUsersChats().add(this);
    }

}
