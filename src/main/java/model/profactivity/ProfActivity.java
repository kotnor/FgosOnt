package model.profactivity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ProfActivity {
    @Getter
    @Setter
    public List<String> objectsProfActivity;

    public static String KEY_NAME = "ХАРАКТЕРИСТИКА ПРОФЕССИОНАЛЬНОЙ ДЕЯТЕЛЬНОСТИ";

    public ProfActivity(String string) {
        objectsProfActivity = new ArrayList<String>();
        parseObjects(string.substring(string.indexOf("4.2"), string.indexOf("4.3")));
    }

    void parseObjects(String string) {
        string = string.substring(string.indexOf("являются") + 8);
        if (string.charAt(0) == ':') {
            string = string.substring(1);
        }
        String[] objects = string.split(";");
        for (int i = 0; i < objects.length; i++) {
            objectsProfActivity.add(objects[i]);
            System.out.println(objects[i]);
        }

    }
}
