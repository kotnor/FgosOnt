package model.profactivity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfActivity implements Serializable {
    @Getter
    @Setter
    public List<String> objectsProfActivity;

    @Getter
    @Setter
    public List<String> typesProfActivity;

    @Getter
    @Setter
    public List<String> areasProfActivity;

    @Getter
    @Setter
    public HashMap<String, List<String>> profTasks;

    public static String KEY_NAME = "ХАРАКТЕРИСТИКА ПРОФЕССИОНАЛЬНОЙ ДЕЯТЕЛЬНОСТИ";

    public ProfActivity(String string) {
        objectsProfActivity = new ArrayList<>();
        typesProfActivity = new ArrayList<>();
        areasProfActivity = new ArrayList<>();
        profTasks = new HashMap<>();
        parseAreas(string.substring(string.indexOf("4.1"), string.indexOf("4.2")));
        parseObjects(string.substring(string.indexOf("4.2"), string.indexOf("4.3")));
        parseTypes(string.substring(string.indexOf("4.3") + 4, string.indexOf("4.4")));
        parseProfTasks(string.substring(string.indexOf("4.4") + 4));
    }

    void parseProfTasks(String string) {
        String areasKeyStartsWord = "профессиональные задачи";
        string = string.substring(string.indexOf(areasKeyStartsWord) + areasKeyStartsWord.length());
        if (string.charAt(0) == ':') {
            string = string.substring(1);
        }
        for (int i = 0; i < typesProfActivity.size(); i++) {
            String type = typesProfActivity.get(i);
            int indexTypeStart = string.indexOf(type);
            String block = string.substring(indexTypeStart);
            block = block.substring(block.indexOf(":") + 1);
            int nextTypeStart = block.indexOf(":");
            List<String> profTasksForType = new ArrayList<>();
            if (nextTypeStart != -1) {
                block = block.substring(0, nextTypeStart);
                String[] profTasksSplitted = block.split(";");
                for (int j = 0; j < profTasksSplitted.length - 1; j++) {
                    profTasksForType.add(profTasksSplitted[j]);
//                    System.out.println("HAHAHA: " + profTasksSplitted[j]);
                }
            } else {
                String[] profTasksSplitted = block.split(";");
                for (int j = 0; j < profTasksSplitted.length - 1; j++) {
                    profTasksForType.add(profTasksSplitted[j]);
//                    System.out.println("HAHAHA: " + profTasksSplitted[j]);
                }
                String last = profTasksSplitted[profTasksSplitted.length - 1];
//                System.out.println("HAHAHALAST: " + last.substring(0, last.indexOf(".")));
                profTasksForType.add(last.substring(0, last.indexOf(".")));
            }
            profTasks.put(type, profTasksForType);
        }
    }

    void parseAreas(String string) {
        String areasKeyStartsWord = "включает";
        string = string.substring(string.indexOf(areasKeyStartsWord) + areasKeyStartsWord.length());
        if (string.charAt(0) == ':') {
            string = string.substring(1);
        }
        String[] areas = string.split(";");
        for (int i = 0; i < areas.length; i++) {
            areasProfActivity.add(areas[i]);
//            System.out.println(areas[i]);
        }
    }

    void parseObjects(String string) {
        string = string.substring(string.indexOf("являются") + 8);
        if (string.charAt(0) == ':') {
            string = string.substring(1);
        }
        String[] objects = string.split(";");
        for (int i = 0; i < objects.length; i++) {
            objectsProfActivity.add(objects[i]);
        }
    }

    void parseTypes(String string) {
        string = string.substring(string.indexOf(':') + 1, string.indexOf('.'));
        String[] types = string.split(";");
        for (int i = 0; i < types.length; i++) {
            typesProfActivity.add(types[i]);
        }
    }
}
