package model;

import lombok.Getter;
import lombok.Setter;
import model.reqtostructure.ReqToStructure;

public class Fgos {
    @Getter @Setter public ResMastering resMastering;
    @Getter @Setter public ReqToStructure reqToStructure;
}
