package ee.taltech.varadehaldamine.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class AssetInfoShort {
//    private String id;
//    private String name;
//    private Boolean active;
//
//    // Address data
//    private String buildingAbbreviationPlusRoom;
//
//    // Person data
//    private String personName;
//
//    private Date modifiedAt;

    private String id;
    private String name;
    private String structuralUnitPlusSubdivision;
    private String mainClassPlusSubclass;
    private String buildingAbbreviationPlusRoom;
    private Integer lifeMonthsLeft;
    private Boolean active;
}
