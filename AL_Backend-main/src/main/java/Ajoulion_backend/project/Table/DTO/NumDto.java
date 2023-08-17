package Ajoulion_backend.project.Table.DTO;


import Ajoulion_backend.project.Table.Entity.Num;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor()
public class NumDto {
    private Long numId;
    private Long totalNum;
    private Long smartphoneNum;
    private Long tabletNum;
    private Long labtopNum;

    public NumDto(Num entity) {
        numId = entity.getNumId();
        totalNum = entity.getTotalNum();
        smartphoneNum = entity.getSmartphoneNum();
        tabletNum = entity.getTabletNum();
        labtopNum = entity.getLabtopNum();
    }
}
