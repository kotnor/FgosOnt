package model.reqtostructure;

import model.MinMax;
import utils.StringUtils;

public class SecondBlock implements Block {
    private final String name = "Дисциплины (модули)";
    private final String BLOCK_TWO = "Блок 2";
    private final String END_BLOCK = "Блок 3";
    private final String VAR_PART = "Вариативная часть";
    private final String NIR = "(НИР)";

    private MinMax blockTwoRange;
    private MinMax varPartRange;
    private double varPartSize = 0;
    private double blockSize = 0;

    public String getName() {
        return name;
    }

    public double getSize() {
        return blockSize;
    }

    public void parseSize(String table) {
        table = findBlockTwo(table);
        blockTwoRange = StringUtils.getMinMax(table.substring(StringUtils.indexOfNextDigit(table, BLOCK_TWO.length())));
        String varPartText = findVarPart(table);
        varPartRange = StringUtils.getMinMax(varPartText.substring(StringUtils.indexOfNextDigit(varPartText, VAR_PART.length())));
    }

    private String findBlockTwo(String table) {
        return table.substring(table.indexOf(BLOCK_TWO), table.indexOf(END_BLOCK));
    }

    private String findVarPart(String table) {
        return table.substring(table.indexOf(VAR_PART));
    }

    public MinMax getBlockTwoRange() {
        return blockTwoRange;
    }

    public void setBlockTwoRange(MinMax blockTwoRange) {
        this.blockTwoRange = blockTwoRange;
    }

    public MinMax getVarPartRange() {
        return varPartRange;
    }

    public void setVarPartRange(MinMax varPartRange) {
        this.varPartRange = varPartRange;
    }

    public double getVarPartSize() {
        return varPartSize;
    }

    public void setVarPartSize(double varPartSize) {
        this.varPartSize = varPartSize;
    }

    public double getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(double blockSize) {
        this.blockSize = blockSize;
    }
}
