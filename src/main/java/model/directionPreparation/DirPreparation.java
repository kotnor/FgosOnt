package model.directionPreparation;

import utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirPreparation {
    private int volume = 120;
    private List<FormOfEducation> formsOfEducation;
    private HashMap<FormOfEducation, Integer> timeOfEducation;

    public enum FormOfEducation {
        FULL_TIME("Очная"), PART_TIME("Очно-заочная"), CORRESPONDENCE("Заочная");

        private final String text;

        FormOfEducation(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public DirPreparation(String block) {
        formsOfEducation = new ArrayList<>();
        parseDirPreparation(block);
        parseFormsOfEducation(block);
    }

    private void parseDirPreparation(String block) {
        parseVolume(block.substring(block.indexOf("3.2") + 4, block.indexOf("3.3")));
    }

    //Parse block from 3.2 to 3.3
    private void parseVolume(String block) {
        int number = StringUtils.nextNumber(block, 0);
        this.volume = number;
    }

    //Parse block from 3.2 to 3.3
    private void parseFormsOfEducation(String block) {
        if (formsOfEducation == null) {
            formsOfEducation = new ArrayList<>();
        }

        if (block.contains(" очной")) {
            formsOfEducation.add(FormOfEducation.FULL_TIME);
        }

        if (block.contains(" заочной")) {
            formsOfEducation.add(FormOfEducation.CORRESPONDENCE);
        }

        if (block.contains("очно-заочной")) {
            formsOfEducation.add(FormOfEducation.PART_TIME);
        }
    }

    private void parseTimeOfEducation(String block) {

    }
}
