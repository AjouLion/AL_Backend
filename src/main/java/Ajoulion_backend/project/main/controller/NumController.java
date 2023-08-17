package Ajoulion_backend.project.main.controller;

import Ajoulion_backend.project.main.service.NumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class NumController {

    @Autowired
    private NumService numService;

    @GetMapping("/main")
    public ResponseEntity<Map<String, Long>> getNumList(@RequestHeader HttpHeaders header) {
        log.info("main");
        Map<String, Long> ret = new HashMap<>();

        ret.put("totalNum", numService.getTotalNum());
        ret.put("smartphoneNum", numService.getTabletNum());
        ret.put("tabletNum", numService.getSmartphoneNum());
        ret.put("labtopNum", numService.getLabtopNum());

        return ResponseEntity.status(HttpStatus.OK).body(ret);
    }
}
