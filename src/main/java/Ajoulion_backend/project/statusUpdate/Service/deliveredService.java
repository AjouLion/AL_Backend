package Ajoulion_backend.project.statusUpdate.Service;

import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Table.Entity.Device;
import Ajoulion_backend.project.statusUpdate.Repository.delivered2Repository;
import Ajoulion_backend.project.statusUpdate.Repository.deliveredRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class deliveredService {

    @Autowired
    private final deliveredRepository deliveredrepository;
    @Autowired
    private final delivered2Repository delivered2repository;

    @Transactional
    public void edit(Long applyId){
        Apply entity = deliveredrepository.findByApplyId(applyId);
        entity.setStatus(4);
        Device entity2 = delivered2repository.findByDeviceId(entity.getDevice().getDeviceId());
        entity2.setStatus(4);
        deliveredrepository.save(entity);
        delivered2repository.save(entity2);

    }


    @Transactional
    public void edit2(Long deviceId, Integer deliverNum, String deliverCorp){
        Device entity = delivered2repository.findByDeviceId(deviceId);
        entity.setStatus(3);
        Apply entity2 = deliveredrepository.findByApplyId(entity.getApply().getApplyId());
        entity2.setStatus(3);
        entity2.setDeliverNum(deliverNum);
        entity2.setDeliverCorp(deliverCorp);
        deliveredrepository.save(entity2);
        delivered2repository.save(entity);

    }


    @Transactional
    public void edit3(Long applyId, Long deviceId){
        Device entity = delivered2repository.findByDeviceId(deviceId);
        //entity.setApplyId(applyId);
        entity.setStatus(2);
        Apply entity2 = deliveredrepository.findByApplyId(applyId);
        //entity2.setDeviceId(deviceId);
        entity2.setStatus(2);
        deliveredrepository.save(entity2);
        delivered2repository.save(entity);

    }
}
