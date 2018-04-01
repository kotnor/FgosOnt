package model.reqtostructure;

import model.MinMax;
import utils.StringUtils;

public class ThirdBlock implements Block {
    private final String name = "Дисциплины (модули)";
    private final String BLOCK_THREE = "Блок 3";
    private final String END_BLOCK = "Блок 3";
    private final String VAR_PART = "Вариативная часть";
    private final String NIR = "(НИР)";

    private MinMax blockThreeRange;
    private double blockSize = 0;

    public String getName() {
        return name;
    }

    public double getSize() {
        return blockSize;
    }

    public void parseSize(String table) {
        table = findBlockThree(table);
        blockThreeRange = StringUtils.getMinMax(table.substring(StringUtils.indexOfNextDigit(table, BLOCK_THREE.length())));
    }

    private String findBlockThree(String table) {
        return table.substring(table.indexOf(BLOCK_THREE));
    }

    public MinMax getBlockThreeRange() {
        return blockThreeRange;
    }

    public void setBlockThreeRange(MinMax blockThreeRange) {
        this.blockThreeRange = blockThreeRange;
    }

    public double getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(double blockSize) {
        this.blockSize = blockSize;
    }
}
