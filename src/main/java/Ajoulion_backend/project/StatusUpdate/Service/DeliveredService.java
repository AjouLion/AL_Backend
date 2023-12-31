package Ajoulion_backend.project.StatusUpdate.Service;

import Ajoulion_backend.project.Error.CustomException;
import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Table.Entity.Device;
import Ajoulion_backend.project.StatusUpdate.Repository.DeviceStatusRepository;
import Ajoulion_backend.project.StatusUpdate.Repository.ApplyStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static Ajoulion_backend.project.Error.ErrorCode.ERR_DEVICETYPE;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class DeliveredService {

    @Autowired
    private final ApplyStatusRepository applyStatusRepository;
    @Autowired
    private final DeviceStatusRepository deviceStatusRepository;

    @Transactional
    public void deliveryComplete(Long applyId){
        Apply apply = applyStatusRepository.findByApplyId(applyId);
        Device device = deviceStatusRepository.findByDeviceId(apply.getDevice().getDeviceId());
        apply.setStatus(4);
        device.setStatus(4);
    }

    @Transactional
    public void updateDeliveryInfo(Long deviceId, String deliverNum, String deliverCorp){
        Device device = deviceStatusRepository.findByDeviceId(deviceId);
        Apply apply = applyStatusRepository.findByApplyId(device.getApply().getApplyId());
        device.setStatus(3);
        apply.setStatus(3);
        apply.setDeliverNum(deliverNum);
        apply.setDeliverCorp(deliverCorp);
    }


    @Transactional
    public void completeMatching(Long applyId, Long deviceId){
        Device device = deviceStatusRepository.findByDeviceId(deviceId);
        Apply apply = applyStatusRepository.findByApplyId(applyId);
        if (!device.getDeviceType().equals(apply.getDeviceType())) {
            throw new CustomException(ERR_DEVICETYPE);
        }
        device.setApply(apply);
        device.setStatus(2);
        apply.setDevice(device);
        apply.setStatus(2);
    }
}
