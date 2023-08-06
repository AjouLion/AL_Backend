package Ajoulion_backend.project.Users.Service;

import Ajoulion_backend.project.Table.DTO.LoginDto;
import Ajoulion_backend.project.Table.DTO.UsersDto;
import Ajoulion_backend.project.Table.Entity.Users;
import Ajoulion_backend.project.Users.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {


    private final UserRepository userRepository;


    //회원 가입
    public void Join(UsersDto usersDto){
        Users findUsers = userRepository.findById(usersDto.getId());
        if (findUsers != null) {
            throw new IllegalStateException("아이디가 중복된 회원입니다.");
        } else {
            Users users = new Users(usersDto);
            userRepository.save(users);
        }
    }

    public UsersDto login(LoginDto loginDto){
        Users user = userRepository.findById(loginDto.getId());
        if (user == null)  return null;
        if (user.getPassword().equals(loginDto.getPassword())) {
            return (new UsersDto(user));
        }
        return null;
    }
}


