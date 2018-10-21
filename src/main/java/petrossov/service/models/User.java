package petrossov.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "user_db")
@Entity
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String hashPassword;
    @Column(name = "date")
    private String date;
    @Column(name = "login")
    private String login;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;



}
