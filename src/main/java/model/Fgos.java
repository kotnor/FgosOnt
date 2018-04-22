package model;

import lombok.Getter;
import lombok.Setter;
import model.profactivity.ProfActivity;
import model.reqtostructure.ReqToStructure;
import model.resmastering.ResMastering;

public class Fgos {
    @Getter @Setter public ResMastering resMastering;
    @Getter @Setter public ReqToStructure reqToStructure;
    @Getter @Setter public ProfActivity profActivity;
}
