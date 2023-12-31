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

    @ManyToOne(fetch = FetchType.LAZY) //DB에서 가져올 때 join하지 않고 나중에 가져오도록
    @JoinColumn(name="userId")
    private Users user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deviceId")
    private Device device;

    @Column(length = 20)
    private String deviceType;
    @Column(length = 100)
    private String address;
    @Column(length = 1000)
    private String content;
    @Column(length = 50)
    private String date;
    private Integer status;
    @Column(length = 50)
    private String deliverNum;
    @Column(length = 50)
    private String deliverCorp;

    public Apply(ApplyDto dto) {

        if (dto.getUserId() != null) { // applyId가 null이 아닌 경우에만 applyId를 설정
            user = new Users();
            user.setUserId(dto.getUserId());
        }

        if (dto.getDeviceId() != null) { // applyId가 null이 아닌 경우에만 applyId를 설정
            device = new Device();
            device.setDeviceId(dto.getDeviceId());
        }

        applyId = dto.getApplyId();
        deviceType = dto.getDeviceType();
        address = dto.getAddress();
        content = dto.getContent();
        date = dto.getDate();
        status = dto.getStatus();
        deliverCorp = dto.getDeliverCorp();
        deliverNum = dto.getDeliverNum();
    }

}