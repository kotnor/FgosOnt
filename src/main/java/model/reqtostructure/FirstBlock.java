package model.reqtostructure;

import model.MinMax;
import utils.StringUtils;

public class FirstBlock implements Block {
    private final String name = "Дисциплины (модули)";
    private final String BLOCK_ONE = "Блок 1";
    private final String END_BLOCK = "Блок 2";
    private final String BASIC_PART = "Базовая часть";
    private final String VAR_PART = "Вариативная часть";

    private MinMax blockOneRange;
    private MinMax basicPartRange;
    private MinMax varPartRange;

    private double basicPartSize = 0;
    private double varPartSize = 0;
    private double blockSize = 0;

    public String getName() {
        return name;
    }

    public double getSize() {
        return blockSize;
    }

    public void parseSize(String table) {
        blockOneRange = StringUtils.getMinMax(table.substring(StringUtils.indexOfNextDigit(table, BLOCK_ONE.length())));
        System.out.println("BlockOneRange: " + blockOneRange);
        String basicPartText = findBasicPart(table);
        basicPartRange = StringUtils.getMinMax(basicPartText.substring(StringUtils.indexOfNextDigit(basicPartText, BASIC_PART.length())));
        System.out.println("BasicPartRange: " + basicPartRange);
        String varPartText = findVarPart(table);
        varPartRange = StringUtils.getMinMax(varPartText.substring(StringUtils.indexOfNextDigit(varPartText, VAR_PART.length())));
        System.out.println("VarPartRange: " + varPartRange);
    }

    private String findBasicPart(String table) {
        return table.substring(table.indexOf(BASIC_PART), table.indexOf(VAR_PART));
    }

    private String findVarPart(String table) {
        return table.substring(table.indexOf(VAR_PART), table.indexOf(END_BLOCK));
    }

    public MinMax getBlockOneRange() {
        return blockOneRange;
    }

    public void setBlockOneRange(MinMax blockOneRange) {
        this.blockOneRange = blockOneRange;
    }

    public MinMax getBasicPartRange() {
        return basicPartRange;
    }

    public void setBasicPartRange(MinMax basicPartRange) {
        this.basicPartRange = basicPartRange;
    }

    public MinMax getVarPartRange() {
        return varPartRange;
    }

    public void setVarPartRange(MinMax varPartRange) {
        this.varPartRange = varPartRange;
    }

    public double getBasicPartSize() {
        return basicPartSize;
    }

    public void setBasicPartSize(double basicPartSize) {
        this.basicPartSize = basicPartSize;
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
