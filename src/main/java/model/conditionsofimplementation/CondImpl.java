package model.conditionsofimplementation;

import utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CondImpl implements Serializable{
    private List<String> elementsElectronicEnvProvision;
    private int minStateEmpl = -1;
    private int maxStateEmpl = -1;
    private int stateEmpl = -1;

    public static String KEY_NAME = "ТРЕБОВАНИЯ К УСЛОВИЯМ РЕАЛИЗАЦИИ";

    public CondImpl(String block) {
        elementsElectronicEnvProvision = new ArrayList<>();
        parseCondImpl(block);
    }

    private void parseCondImpl(String block) {
        parseElementsElectronicEnvProvision(block.substring(block.indexOf("7.1.2"), block.indexOf("7.1.3")));
        parseStateEmployeers(block.substring(block.indexOf("7.1.6"), block.indexOf("7.1.7")));
    }

    //Parse from 7.1.2 to 7.1.3
    private void parseElementsElectronicEnvProvision(String block) {
        block = block.substring(block.indexOf("обеспечивать") + 12);
        if (block.charAt(0) == ':') {
            block.substring(1, block.indexOf("."));
        } else {
            block.substring(0, block.indexOf("."));
        }
        String[] objects = block.split(";");
        for (int i = 0; i < objects.length; i++) {
            elementsElectronicEnvProvision.add(objects[i]);
            System.out.println("HAHAHA:" + objects[i]);
        }
    }

    //Parse 7.1.6
    private void parseStateEmployeers(String block) {
        block = block.substring(5);
        int value = StringUtils.nextNumber(block, 0);
        System.out.println("STATE EMPLOYEESS: " + value);
        if (block.contains("не менее")) {
            minStateEmpl = value;
        } else if (block.contains("не более")) {
            maxStateEmpl = value;
        } else {
            stateEmpl = value;
        }
    }
}
