package Ajoulion_backend.project.Table.Entity;

import Ajoulion_backend.project.Table.DTO.UsersDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String profileUrl;
    @Column(length = 300)
    private String certification;
    private Integer category;

    @OneToMany(mappedBy = "user")
    private List<Apply> apply = new ArrayList<Apply>();

    @OneToMany(mappedBy = "user")
    private List<Device> device = new ArrayList<Device>();

    public Users(UsersDto dto) {
        userId = dto.getUserId();
        name = dto.getName();
        id = dto.getId();
        password = dto.getPassword();
        profileUrl = dto.getProfileUrl();
        certification = dto.getCertification();
        category = dto.getCategory();
    }

}
