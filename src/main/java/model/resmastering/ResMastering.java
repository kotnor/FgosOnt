package model.resmastering;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResMastering {
    @Getter @Setter public List<String> commonCompetencies;
    @Getter @Setter public List<String> commonProfCompetencies;
    @Getter @Setter public List<String> profCompetencies;
    public static String KEY_NAME = "ТРЕБОВАНИЯ К РЕЗУЛЬТАТАМ ОСВОЕНИЯ ПРОГРАММЫ МАГИСТРАТУРЫ";

    public ResMastering(String block) {
        commonCompetencies = new ArrayList<String>();
        commonProfCompetencies = new ArrayList<String>();
        profCompetencies = new ArrayList<String>();

        parse(block);
    }

    private void parse(String block) {
        System.out.println("Block!");
        System.out.println(block);
        parseOK(block.substring(block.indexOf("5.2"), block.indexOf("5.3")));
        parseOPK(block.substring(block.indexOf("5.3"), block.indexOf("5.4")));
        parsePK(block.substring(block.indexOf("5.4"), block.indexOf("5.5")));

        for (int i = 0; i < commonCompetencies.size(); i++) {
            // System.out.println("ОК-" + (i + 1) + " : " + commonCompetencies.get(i));
        }

        for (int i = 0; i < commonProfCompetencies.size(); i++) {
            // System.out.println("ОПК-" + (i + 1) + " : " + commonProfCompetencies.get(i));
        }

        for (int i = 0; i < profCompetencies.size(); i++) {
            // System.out.println("ПК-" + (i + 1) + " : " + profCompetencies.get(i));
        }
    }

    private void parseOPK(String text) {
        parseCompetencies(text, "(ОПК-", commonProfCompetencies);
    }

    private void parsePK(String text) {
        parseCompetencies(text, "(ПК-", profCompetencies);
    }

    private void parseOK(String text) {
        parseCompetencies(text, "(ОК-", commonCompetencies);
    }

    private void parseCompetencies(String text, String key, List<String> list) {
        text = text.substring(text.indexOf(":") + 1); // remove header
        String ok[] = text.split(";");
        System.out.println(ok.length);
        for (int i = 0; i < ok.length; i++) {
            String okItem = ok[i];
            String description = okItem.substring(0, ok[i].indexOf(key));
            list.add(description);
        }
    }
}
