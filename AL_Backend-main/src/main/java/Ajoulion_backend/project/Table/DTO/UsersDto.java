package Ajoulion_backend.project.Table.DTO;

import Ajoulion_backend.project.Table.Entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor()
public class UsersDto {
    private Long userId;
    private String name;
    private String id;
    private String password;
    private String profileUrl;
    private String certification;
    private Integer category;

    public UsersDto(Users entity) {
        userId = entity.getUserId();
        name = entity.getName();
        id = entity.getId();
        password = entity.getPassword();
        profileUrl = entity.getProfileUrl();
        certification = entity.getCertification();
        category = entity.getCategory();
    }
}
