package model.reqtostructure;

import model.MinMax;
import utils.StringUtils;

import java.util.ArrayList;

public class ReqToStructure {
    private FirstBlock firstBlock;
    private SecondBlock secondBlock;
    private ThirdBlock thirdBlock;
    private MinMax commonSize;
    private final String COMMON_SIZE_NAME = "Объем программы магистратуры";

    public static String KEY_NAME = "ТРЕБОВАНИЯ К СТРУКТУРЕ ПРОГРАММЫ МАГИСТРАТУРЫ";

    public ReqToStructure(String block) {
        parse(block);
    }

    private void parse(String block) {
        firstBlock = new FirstBlock();
        secondBlock = new SecondBlock();
        thirdBlock = new ThirdBlock();
        String blocksSize = findBlocksSizeInTable(findTable(block));
        firstBlock.parseSize(blocksSize);
        secondBlock.parseSize(blocksSize);
        thirdBlock.parseSize(blocksSize);
        commonSize = StringUtils.getMinMax(blocksSize.substring(blocksSize.indexOf((COMMON_SIZE_NAME))));
    }

    private String findTable(String block) {
        return block.substring(block.indexOf("Таблица"), block.indexOf("6.3"));
    }

    private String findBlocksSizeInTable(String table) {
        return table.substring(table.indexOf("Блок 1"));
    }

    public FirstBlock getFirstBlock() {
        return firstBlock;
    }

    public void setFirstBlock(FirstBlock firstBlock) {
        this.firstBlock = firstBlock;
    }

    public SecondBlock getSecondBlock() {
        return secondBlock;
    }

    public void setSecondBlock(SecondBlock secondBlock) {
        this.secondBlock = secondBlock;
    }

    public ThirdBlock getThirdBlock() {
        return thirdBlock;
    }

    public void setThirdBlock(ThirdBlock thirdBlock) {
        this.thirdBlock = thirdBlock;
    }

    public MinMax getCommonSize() {
        return commonSize;
    }

    public void setCommonSize(MinMax commonSize) {
        this.commonSize = commonSize;
    }
}
