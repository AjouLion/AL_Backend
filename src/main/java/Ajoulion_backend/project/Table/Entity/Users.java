package Ajoulion_backend.project.Table.Entity;

import Ajoulion_backend.project.Table.DTO.UsersDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor()
@Table(name= "Users")
public class Users {
    @Id@GeneratedValue
    private Long userId;
    @Column(length = 10)
    private String name;
    @Column(length = 10)
    private String id;
    @Column(length = 20)
    private String password;
    @Column(length = 300)
    private String profile;

    public Users(UsersDto dto) {
        userId = dto.getUserId();
        name = dto.getName();
        id = dto.getId();
        password = dto.getPassword();
        profile = dto.getProfile();
    }

}
