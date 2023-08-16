package Ajoulion_backend.project.Table.DTO;

import Ajoulion_backend.project.Table.Entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor()
public class UsersDto {
    private Long userId;
    private String name;
    private String id;
    private String password;
    private String profile;

    public UsersDto(Users entity) {
        userId = entity.getUserId();
        name = entity.getName();
        id = entity.getId();
        password = entity.getPassword();
        profile = entity.getProfile();
    }
}
