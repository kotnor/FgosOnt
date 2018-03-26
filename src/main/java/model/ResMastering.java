package model;

import java.util.HashMap;

public class ResMastering {
    private HashMap<String, String> commonCompetencies;
    private HashMap<String, String> commonProfCompetencies;
    private HashMap<String, String> profCompetencies;
    public static String KEY_NAME = "ТРЕБОВАНИЯ К РЕЗУЛЬТАТАМ ОСВОЕНИЯ ПРОГРАММЫ МАГИСТРАТУРЫ";

    public ResMastering(String block) {
        commonCompetencies = new HashMap<String, String>();
        commonProfCompetencies = new HashMap<String, String>();
        profCompetencies = new HashMap<String, String>();

        parse(block);
    }

    private void parse(String block) {
        block = block.substring(block.indexOf(":") + 1, block.lastIndexOf(").") + 2);
        
        System.out.println(block);
        int startIndexCommon = -1;
        int endIndexCommon = -1;
        int startIndexCommonProf = -1;
        int endIndexCommonProf = -1;
        int startIndexProf = -1;
        int endIndexProf = -1;

        String[] lines = block.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("(ОК-")) {
                startIndexCommon = i;
                //System.out.println(i + " " + lines[i]);
                break;
            }
        }

        for (int i = lines.length - 1; i >= 0; i--) {
            if (lines[i].contains("(ОК-")) {
                endIndexCommon = i;
                //System.out.println(i + " " + lines[i]);
                break;
            }
        }

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("(ОПК-")) {
                startIndexCommonProf = i;
            }
        }

        for (int i = lines.length - 1; i >= 0; i--) {
            if (lines[i].contains("(ОПК-")) {
                endIndexCommonProf = i;
            }
        }

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("(ПК-")) {
                startIndexProf = i;
            }
        }

        for (int i = lines.length - 1; i >= 0; i--) {
            if (lines[i].contains("(ПК-")) {
                endIndexProf = i;
            }
        }
        System.out.println("ОК: " + (endIndexCommon - startIndexCommon + 1));
    }

    private void parseOK() {

    }
}
