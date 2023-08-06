package Ajoulion_backend.project.Table.Entity;

import Ajoulion_backend.project.Table.DTO.ApplyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor()
@Table(name= "Apply")
public class Apply {
    @Id@GeneratedValue
    private Long applyId;
    private Long userId;
    private Long deviceId;
    private Integer deviceType;
    @Column(length = 100)
    private String address;
    @Column(length = 1000)
    private String content;
    @Column(length = 20)
    private String date;
    private Integer status;

    public Apply(ApplyDto dto) {
        applyId = dto.getApplyId();
        userId = dto.getUserId();
        deviceId = dto.getDeviceId();
        deviceType = dto.getDeviceType();
        address = dto.getAddress();
        content = dto.getContent();
        date = dto.getDate();
        status = dto.getStatus();
    }

}