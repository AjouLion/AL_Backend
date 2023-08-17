package Ajoulion_backend.project.Table.DTO;

import Ajoulion_backend.project.Table.Entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor()
public class UserSimpleDto {
    private Long userId;
    private String id;
    private String name;
    private String profile;
    private Integer category;

    public UserSimpleDto(Users entity) {
        userId = entity.getUserId();
        id = entity.getId();
        name = entity.getName();
        profile = entity.getProfile();
        category = entity.getCategory();
    }
}
