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
@Table(name= "Device")
public class Device {
    @Id@GeneratedValue
    private Long deviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private Users user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="applyId")
    private Apply apply;

    private String deviceType;
    @Column(length = 50)
    private String model;
    @Column(length = 20)
    private String date;
    private Integer conditions;
    @Column(length = 300)
    private String image;
    private Integer status;
    @Column(length = 100)
    private String usedDate;

    public Device(DeviceDto dto) {
        deviceId = dto.getDeviceId();
        user.setUserId(dto.getUserId());
        apply.setApplyId(dto.getApplyId());
        deviceType = dto.getDeviceType();
        model = dto.getModel();
        date = dto.getDate();
        conditions = dto.getConditions();
        image = dto.getImage();
        status = dto.getStatus();
        usedDate = dto.getUsedDate();
    }

}
