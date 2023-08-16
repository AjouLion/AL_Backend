package Ajoulion_backend.project.StatusUpdate.Service;

import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Table.Entity.Device;
import Ajoulion_backend.project.StatusUpdate.Repository.DeviceStatusRepository;
import Ajoulion_backend.project.StatusUpdate.Repository.ApplyStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public void edit(Long applyId){
        Apply entity = applyStatusRepository.findByApplyId(applyId);
        entity.setStatus(4);
        Device entity2 = deviceStatusRepository.findByDeviceId(entity.getDevice().getDeviceId());
        entity2.setStatus(4);
        applyStatusRepository.save(entity);
        deviceStatusRepository.save(entity2);

    }

    @Transactional
    public void edit2(Long deviceId, Integer deliverNum, String deliverCom){
        Device entity = deviceStatusRepository.findByDeviceId(deviceId);
        entity.setStatus(3);
        Apply entity2 = applyStatusRepository.findByApplyId(entity.getApply().getApplyId());
        entity2.setStatus(3);
        entity2.setDeliverNum(deliverNum);
        entity2.setDeliverCorp(deliverCom);
        applyStatusRepository.save(entity2);
        deviceStatusRepository.save(entity);

    }


    @Transactional
    public void edit3(Long applyId, Long deviceId){
        Device entity = deviceStatusRepository.findByDeviceId(deviceId);
        entity.getApply().setApplyId(applyId);
        entity.setStatus(2);
        Apply entity2 = applyStatusRepository.findByApplyId(applyId);
        entity2.getDevice().setDeviceId(deviceId);
        entity2.setStatus(2);
        applyStatusRepository.save(entity2);
        deviceStatusRepository.save(entity);

    }
}
