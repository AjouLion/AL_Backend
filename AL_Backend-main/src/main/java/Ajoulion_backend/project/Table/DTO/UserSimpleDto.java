package Ajoulion_backend.project.Table.DTO;

import Ajoulion_backend.project.Table.Entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor()
public class UserSimpleDto {
    private Long userId;
    private String id;
    private String name;
    private String profileUrl;
    private Integer category;

    public UserSimpleDto(Users entity) {
        userId = entity.getUserId();
        id = entity.getId();
        name = entity.getName();
        profileUrl = entity.getProfileUrl();
        category = entity.getCategory();
    }
}
