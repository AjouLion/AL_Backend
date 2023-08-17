package Ajoulion_backend.project.Table.DTO;

import Ajoulion_backend.project.Table.Entity.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor()
public class DeviceDto {
    private Long deviceId;
    private Long userId;
    private Long applyId;
    private String deviceType;
    private String model;
    private String date;
    private Integer conditions;
    private String image;
    private Integer status;
    private String usedDate;

    public DeviceDto(Device entity) {
        deviceId = entity.getDeviceId();
        userId = entity.getUser().getUserId();
        applyId = entity.getApply().getApplyId();
        deviceType = entity.getDeviceType();
        model = entity.getModel();
        date = entity.getDate();
        conditions = entity.getConditions();
        image = entity.getImage();
        status = entity.getStatus();
        usedDate = entity.getUsedDate();
    }
}
