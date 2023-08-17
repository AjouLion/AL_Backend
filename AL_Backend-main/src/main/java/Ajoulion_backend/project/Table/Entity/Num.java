package Ajoulion_backend.project.Table.Entity;


import Ajoulion_backend.project.Table.DTO.NumDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor()
@Table(name= "Num")
public class Num {

    @Id @GeneratedValue
    private Long numId;
    private Long totalNum;
    private Long smartphoneNum;
    private Long tabletNum;
    private Long labtopNum;

    public Num(NumDto dto){
        numId = dto.getNumId();
        totalNum = dto.getTotalNum();
        smartphoneNum = dto.getSmartphoneNum();
        tabletNum = dto.getTabletNum();
        labtopNum = dto.getLabtopNum();
    }

}
