package model.profactivity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ProfActivity {
    @Getter
    @Setter
    public List<String> objectsProfActivity;

    @Getter
    @Setter
    public List<String> typesProfActivity;

    @Getter
    @Setter
    public List<String> areasProfActivity;

    public static String KEY_NAME = "ХАРАКТЕРИСТИКА ПРОФЕССИОНАЛЬНОЙ ДЕЯТЕЛЬНОСТИ";

    public ProfActivity(String string) {
        objectsProfActivity = new ArrayList<>();
        typesProfActivity = new ArrayList<>();
        areasProfActivity = new ArrayList<>();
        parseAreas(string.substring(string.indexOf("4.1"), string.indexOf("4.2")));
        parseObjects(string.substring(string.indexOf("4.2"), string.indexOf("4.3")));
        parseTypes(string.substring(string.indexOf("4.3") + 4, string.indexOf("4.4")));
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
            System.out.println(areas[i]);
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
