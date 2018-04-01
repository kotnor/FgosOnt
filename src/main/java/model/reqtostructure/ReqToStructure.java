package model.reqtostructure;

import java.util.ArrayList;

public class ReqToStructure {
    private FirstBlock firstBlock;
    private SecondBlock secondBlock;
    private ThirdBlock thirdBlock;

    public static String KEY_NAME = "ТРЕБОВАНИЯ К СТРУКТУРЕ ПРОГРАММЫ МАГИСТРАТУРЫ";

    public ReqToStructure(String block) {
        firstBlock = new FirstBlock();
        secondBlock = new SecondBlock();
        thirdBlock = new ThirdBlock();
        //parse(block);
        System.out.println(block);
        String blocksSize = findBlocksSizeInTable(findTable(block));
        firstBlock.parseSize(blocksSize);
        secondBlock.parseSize(blocksSize);
    }

    private void parse(String block) {

    }

    private String findTable(String block) {
        return block.substring(block.indexOf("Таблица"), block.indexOf("6.3"));
    }

    private String findBlocksSizeInTable(String table) {
        return table.substring(table.indexOf("Блок 1"));
    }
}
