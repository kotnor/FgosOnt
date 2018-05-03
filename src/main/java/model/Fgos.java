package model;

import lombok.Getter;
import lombok.Setter;
import model.conditionsofimplementation.CondImpl;
import model.profactivity.ProfActivity;
import model.reqtostructure.ReqToStructure;
import model.resmastering.ResMastering;

import java.io.Serializable;

public class Fgos implements Serializable {
    @Getter @Setter public ResMastering resMastering;
    @Getter @Setter public ReqToStructure reqToStructure;
    @Getter @Setter public ProfActivity profActivity;
    @Getter @Setter public CondImpl condImpl;
}
