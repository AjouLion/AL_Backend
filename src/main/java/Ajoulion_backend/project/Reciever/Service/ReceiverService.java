package Ajoulion_backend.project.Reciever.Service;

import Ajoulion_backend.project.Error.CustomException;
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

import static Ajoulion_backend.project.Error.ErrorCode.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReceiverService {
    private final ReceiverRepository recvRepository;

    public List<Map<String, Object>> getApplyList() {
        List<Apply> list = recvRepository.findByUser_IdNotAndStatusOrderByApplyIdDesc("deleted", 1);
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
    public Apply getApply(Long applyId) {
        Apply apply = recvRepository.findByApplyId(applyId);
        if (apply == null) {
            throw new CustomException(ERR_APPLY_NOT_EXIST);
        }
        return apply;
    }

    public ApplyDto getApplyInfo(Long applyId) {
        return new ApplyDto(getApply(applyId));
    }

    public Long save(ApplyDto dto) {
        Apply apply = new Apply(dto);
        recvRepository.save(apply);
        return apply.getApplyId();
    }

    @Transactional
    public void update(Long applyId, ApplyDto applyDto) {
        Apply apply = getApply(applyId);
        apply.setDeviceType(applyDto.getDeviceType());
        apply.setAddress(applyDto.getAddress());
        apply.setContent(applyDto.getContent());
    }

    public void deleteByApplyId(Long deviceId) {
        recvRepository.deleteById(deviceId);
    }
}
