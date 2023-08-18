package Ajoulion_backend.project.main.service;

import Ajoulion_backend.project.main.repository.NumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NumService {
    @Autowired
    private final NumRepository numRepository;

    public long getTotalNum(){
        return numRepository.countByStatus(4);
    }

    public long getTabletNum(){
        return numRepository.countByStatusAndDeviceType(4, "태블릿");
    }

    public long getSmartphoneNum(){
        return numRepository.countByStatusAndDeviceType(4, "스마트폰");
    }

    public long getLabtopNum(){
        return numRepository.countByStatusAndDeviceType(4, "노트북");
    }


}
