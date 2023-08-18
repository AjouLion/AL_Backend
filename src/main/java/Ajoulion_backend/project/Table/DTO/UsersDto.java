package Ajoulion_backend.project.Table.DTO;

import Ajoulion_backend.project.ImageUpload.ImageUpload;
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
    private String certification;
    private Integer category;
    private Boolean isDeleted;

    public UsersDto(Users entity) {
        userId = entity.getUserId();
        name = entity.getName();
        id = entity.getId();
        password = entity.getPassword();
        profile = ImageUpload.getProfilePath(userId, entity.getProfile());
        certification = ImageUpload.getCertificationPath(userId, entity.getCertification());
        category = entity.getCategory();
        isDeleted = entity.getIsDeleted();
    }
}
