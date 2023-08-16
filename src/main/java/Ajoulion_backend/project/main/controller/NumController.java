package Ajoulion_backend.project.main.controller;

import Ajoulion_backend.project.Table.DTO.NumDto;
import Ajoulion_backend.project.main.service.NumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class NumController {

    @Autowired
    private NumService numService;

    @GetMapping("/main")
    public ResponseEntity<NumDto> getNumList(@RequestHeader HttpHeaders header) {
        log.info("main");

        NumDto numList = new NumDto();
        numList.setTotalNum(numService.getTotalNum());
        numList.setTabletNum(numService.getTabletNum());
        numList.setSmartphoneNum(numService.getSmartphoneNum());
        numList.setLabtopNum(numService.getLabtopNum());

        return ResponseEntity.status(HttpStatus.OK).body(numList);
    }
}
