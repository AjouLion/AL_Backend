package Ajoulion_backend.project.Table.DTO;

import Ajoulion_backend.project.Table.Entity.Apply;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor()
public class ApplyDto {
    private Long applyId;
    private Long userId;
    private Long deviceId;
    private Integer deviceType;
    private String address;
    private String content;
    private String date;
    private Integer status;

    public ApplyDto(Apply entity) {
        applyId = entity.getApplyId();
        userId = entity.getUserId();
        deviceId = entity.getDeviceId();
        deviceType = entity.getDeviceType();
        address = entity.getAddress();
        content = entity.getContent();
        date = entity.getDate();
        status = entity.getStatus();
    }
}


