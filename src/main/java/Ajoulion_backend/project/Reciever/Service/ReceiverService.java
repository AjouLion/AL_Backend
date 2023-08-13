package Ajoulion_backend.project.Reciever.Service;

import Ajoulion_backend.project.Reciever.Repository.ReceiverRepository;
import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Table.DTO.UserSimpleDto;
import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Users.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReceiverService {
    private final ReceiverRepository recvRepository;
    private final UserRepository userRepository;

    public List<Map<String, Object>> getApplyList() {
        List<Apply> list = recvRepository.findAllByOrderByApplyIdDesc();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Apply apply : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("apply", new ApplyDto(apply));
            map.put("user", new UserSimpleDto(apply.getUser()));
            mapList.add(map);
        }
        return mapList;
    }

    public List<ApplyDto> getApplyListForUser(Long userId) {
        List<Apply> list = recvRepository.findByUser_UserIdOrderByApplyIdDesc(userId); // Apply Entity user객체의 userId 변수
        List<ApplyDto> applyDtoList = new ArrayList<>();
        for (Apply apply : list) {
            applyDtoList.add(new ApplyDto(apply));
        }
        return applyDtoList;
    }

    public ApplyDto getApplyInfo(Long userId) {
        Apply apply = recvRepository.findByApplyId(userId);
        return (new ApplyDto(apply));
    }

    public void save(ApplyDto dto) {
        Apply entity = new Apply(dto);
        recvRepository.save(entity);
    }

    public void update(Long applyId, ApplyDto applyDto) {
        Apply entity = recvRepository.findByApplyId(applyId);
        entity.setDeviceType(applyDto.getDeviceType());
        entity.setDate(applyDto.getDate());
        entity.setAddress(applyDto.getAddress());
        entity.setContent(applyDto.getContent());
        entity.setStatus(applyDto.getStatus());
        recvRepository.save(entity);
    }

    public void deleteByApplyId(Long deviceId) {
        recvRepository.deleteById(deviceId);
    }
}
