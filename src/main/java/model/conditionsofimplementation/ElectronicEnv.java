package model.conditionsofimplementation;

import java.util.ArrayList;
import java.util.List;

public class ElectronicEnv {
    List<String> elementsProvision;
    public ElectronicEnv(String block) {
        elementsProvision = new ArrayList<>();
        parseElectronicEnv(block);
    }

    private void parseElectronicEnv(String block) {

    }
}
