package com.company;

import javax.swing.*;

class SingleField extends JButton {
    private int row;
    private int col;
    private int plotID;
    private boolean isMine = false;
    private int minesAroundCount;
    static private int nextPlotID;

    //constructors
    public SingleField(int r, int c, boolean iM, Icon icon, ImageIcon disabledIcon) {
        super(icon);
        setDisabledIcon(disabledIcon);
        plotID = nextPlotID;
        nextPlotID++;
        row = r;
        col = c;
        isMine = iM;
    }
    public SingleField(int r, int c, Icon icon, ImageIcon uncoveredIcon) {
        super(icon);
        plotID = nextPlotID;
        nextPlotID++;
        row = r;
        col = c;
    }


    public void PlantBomb(int row, int col) {


    }

    //setters and getters
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getPlotID() {
        return plotID;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean b) {
        isMine = b;
    }

    public int getMinesAroundCount() {
        return minesAroundCount;
    }

    public void setMinesAroundCount(int minesAroundCount) {
        this.minesAroundCount = minesAroundCount;
    }

    public void incMinesAroundCount() {
        minesAroundCount++;
    }
}
