package Ajoulion_backend.project.Table.Entity;

import Ajoulion_backend.project.Table.DTO.DeviceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor()
@Table(name= "Device", indexes = @Index(name = "idx_deviceType", columnList = "deviceType"))
public class Device {
    @Id@GeneratedValue
    private Long deviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private Users user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="applyId")
    private Apply apply;
    @Column(length = 20)
    private String deviceType;
    @Column(length = 50)
    private String model;
    @Column(length = 50)
    private String date;
    private Integer conditions;
    @Column(length = 300)
    private String image;
    private Integer status;
    @Column(length = 100)
    private String usedDate;

    public Device(DeviceDto dto) {

        if (dto.getUserId() != null) { // applyId가 null이 아닌 경우에만 applyId를 설정
            user = new Users();
            user.setUserId(dto.getUserId());
        }

        if (dto.getApplyId() != null) { // applyId가 null이 아닌 경우에만 applyId를 설정
            apply = new Apply();
            apply.setApplyId(dto.getApplyId());
        }

        deviceId = dto.getDeviceId();
        deviceType = dto.getDeviceType();
        model = dto.getModel();
        date = dto.getDate();
        conditions = dto.getConditions();
        image = dto.getImage();
        status = dto.getStatus();
        usedDate = dto.getUsedDate();
    }

}
