package Ajoulion_backend.project.Reciever.Service;

import Ajoulion_backend.project.Reciever.Repository.ReceiverRepository;
import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Table.DTO.UsersDto;
import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Table.Entity.Users;
import Ajoulion_backend.project.Users.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReceiverService {

    private final ReceiverRepository recvRepository;
    private final UserRepository userRepository;

    public List<ApplyDto> getApplyList() {
        List<Apply> applyList = recvRepository.findAll();
        List<ApplyDto> applyDtoList = new ArrayList<>();
        for (Apply apply : applyList) {
            applyDtoList.add(new ApplyDto(apply));
        }
        return applyDtoList;
    }

    public ApplyDto getApplyInfo(Long userId) {
        Apply apply = recvRepository.findByApplyId(userId);
        return (new ApplyDto(apply));
    }

    public UsersDto getUserInfo(Long userId) {
        Users user = userRepository.findByUserId(userId);
        return (new UsersDto(user));
    }

    public ResponseEntity logincheck(HttpServletRequest request){
        HttpSession session = request.getSession();
        UsersDto user = (UsersDto) session.getAttribute("login");
        log.info("login checking . . . ");
        if (user != null) return null;
        log.info("login check fail");
        return ResponseEntity.created(URI.create("/users/login")).build();
    }
}
