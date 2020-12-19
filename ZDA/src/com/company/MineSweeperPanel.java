package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperPanel extends JPanel {

    private JLabel mineLabel;

    private JPanel minefield;
    private int minefieldRows = 9;
    private int minefieldColumns = 9;
    private int gamePlots = minefieldRows * minefieldColumns;
    private int mines = 10;
    private int nonMineMfields = gamePlots - mines;

    JFrame defeatPane;

    //load game icons

    private final ImageIcon coveredIcon = new ImageIcon(".\\src\\com\\company\\icons\\covered.gif");
    private final ImageIcon uncoveredIcon = new ImageIcon(".\\src\\com\\company\\icons\\uncovered.gif");
    private final ImageIcon flagIcon = new ImageIcon(".\\src\\com\\company\\icons\\flag.gif");
    private final ImageIcon bombIcon =  new ImageIcon(".\\src\\com\\company\\icons\\bomb.gif");
    private final ImageIcon redBombIcon =  new ImageIcon(".\\src\\com\\company\\icons\\redBomb.gif");
    private final ImageIcon oneIcon =  new ImageIcon(".\\src\\com\\company\\icons\\one.gif");
    private final ImageIcon twoIcon =  new ImageIcon(".\\src\\com\\company\\icons\\two.gif");
    private final ImageIcon threeIcon =  new ImageIcon(".\\src\\com\\company\\icons\\three.gif");
    private final ImageIcon fourIcon =  new ImageIcon(".\\src\\com\\company\\icons\\four.gif");
    private final ImageIcon fiveIcon =  new ImageIcon(".\\src\\com\\company\\icons\\five.gif");
    private final ImageIcon sixIcon =  new ImageIcon(".\\src\\com\\company\\icons\\six.gif");
    private final ImageIcon sevenIcon =  new ImageIcon(".\\src\\com\\company\\icons\\seven.gif");
    private final ImageIcon eightIcon =  new ImageIcon(".\\src\\com\\company\\icons\\eight.gif");


<<<<<<< Updated upstream
    public MineSweeperPanel() {
        setLayout(new BorderLayout());

        // add mine display
        mineLabel = new JLabel(String.valueOf(mines), JLabel.CENTER);
        mineLabel.setEnabled(true);
        add(mineLabel, BorderLayout.NORTH);

=======
    public MineSweeperPanel(int minefieldRows, int minefieldColumns, int mines) {
>>>>>>> Stashed changes
        // add minefield in a grid
        minefield = new JPanel();
        minefield.setLayout(new GridLayout(minefieldRows, minefieldColumns));
        fillMinefileld();
        add(minefield, BorderLayout.CENTER);

    }

    private SingleField[][] fields = new SingleField[minefieldColumns][minefieldRows];

    private void fillMinefileld() {
        var fieldCount = minefieldColumns * minefieldRows;
        var j = mines;
        for (int r = 0; r < minefieldRows; r++) {
            for (int c = 0; c < minefieldColumns; c++)
                addField(r, c);
        }

        while (mines != 0) {
            var row = (int) (Math.random() * minefieldRows);
            var col = (int) (Math.random() * minefieldColumns);
            if (!fields[row][col].isMine()) {
                plantMine(row, col);
                mines--;
            }

        }

    }

    private void addField(int r, int c) {
        var field = new SingleField(r, c, coveredIcon, uncoveredIcon);
        field.addActionListener(new uncoverAction());
        fields[r][c] = field;
        minefield.add(field);
    }

    private void plantMine(int r, int c) {
        fields[r][c].setMine(true);
        checkMinesAround(r, c);
//        fields[r][c].setIcon(bombIcon);

    }
    private void checkMinesAround (int r, int c) {
        if (r - 1 >= 0 && c - 1 >= 0) increaseMinesAroundCounter(r-1, c-1);
        if (r - 1 >= 0 && c >= 0) increaseMinesAroundCounter(r-1, c);
        if (r - 1 >= 0 && c + 1 <= (minefieldColumns-1)) increaseMinesAroundCounter(r-1, c+1);

        if (c - 1 >= 0) increaseMinesAroundCounter(r, c-1);
        if (c + 1 <= (minefieldColumns-1)) increaseMinesAroundCounter(r, c+1);

        if (r + 1 <= (minefieldRows-1) && c - 1 >= 0) increaseMinesAroundCounter(r+1, c-1);
        if (r + 1 <= (minefieldRows-1) && c >= 0) increaseMinesAroundCounter(r+1, c);
        if (r + 1 <= (minefieldRows-1) && c + 1 <= (minefieldColumns-1)) increaseMinesAroundCounter(r+1, c+1);
    }

    private void increaseMinesAroundCounter (int r, int c) {
        if (!fields[r][c].isMine()) {
            fields[r][c].incMinesAroundCount();
            setMinesAroundIcon(r, c);
        }
    }

//    private void setMinesAroundIcon(int r, int c) {
//        switch (fields[r][c].getMinesAroundCount()) {
//            case 1 -> {fields[r][c].setIcon(oneIcon); fields[r][c].setDisabledIcon(oneIcon);}
//            case 2 -> {fields[r][c].setIcon(twoIcon); fields[r][c].setDisabledIcon(twoIcon);}
//            case 3 -> {fields[r][c].setIcon(threeIcon); fields[r][c].setDisabledIcon(threeIcon);}
//            case 4 -> {fields[r][c].setIcon(fourIcon); fields[r][c].setDisabledIcon(fourIcon);}
//            case 5 -> {fields[r][c].setIcon(fiveIcon); fields[r][c].setDisabledIcon(fiveIcon);}
//            case 6 -> {fields[r][c].setIcon(sixIcon); fields[r][c].setDisabledIcon(sixIcon);}
//            case 7 -> {fields[r][c].setIcon(sevenIcon); fields[r][c].setDisabledIcon(sevenIcon);}
//            case 8 -> {fields[r][c].setIcon(eightIcon); fields[r][c].setDisabledIcon(eightIcon);}
//        }
//    }
    private void setMinesAroundIcon(int r, int c) {
        switch (fields[r][c].getMinesAroundCount()) {
            case 1 -> fields[r][c].setDisabledIcon(oneIcon);
            case 2 -> fields[r][c].setDisabledIcon(twoIcon);
            case 3 -> fields[r][c].setDisabledIcon(threeIcon);
            case 4 -> fields[r][c].setDisabledIcon(fourIcon);
            case 5 -> fields[r][c].setDisabledIcon(fiveIcon);
            case 6 -> fields[r][c].setDisabledIcon(sixIcon);
            case 7 -> fields[r][c].setDisabledIcon(sevenIcon);
            case 8 -> fields[r][c].setDisabledIcon(eightIcon);
        }
    }

    //end of setup

    private class uncoverAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            var sourceField = (SingleField) event.getSource();
            if (sourceField.isMine()) {
                revealAllMines();
                sourceField.setDisabledIcon(redBombIcon);
                defeatPane = new JFrame();
                JOptionPane.showMessageDialog(defeatPane, "You lost!", "Defeat", JOptionPane.ERROR_MESSAGE);

            } else {
                revealSurroundingPlots(sourceField.getRow(), sourceField.getCol());
//                sourceField.setEnabled(false);
            }

            //check win condition
//            if (disabledFields == nonMineMfields) win;
//                sourceField.setEnabled(false);
//            sourceField.setMine(true);
//            sourceField.setIcon(bombIcon);
        }

    }

    private void revealAllMines() {
        for (int r = 0; r < minefieldRows; r++) {
            for (int c = 0; c < minefieldColumns; c++)
                if (fields[r][c].isMine()) {
                    fields[r][c].setDisabledIcon(bombIcon);
                    fields[r][c].setEnabled(false);
                }
        }
    }

    private void revealSurroundingPlots(int r, int c) {

        fields[r][c].setEnabled(false);
        if (fields[r][c].getMinesAroundCount() == 0) {
            if (r - 1 >= 0 && c - 1 >= 0 && fields[r - 1][c - 1].isEnabled() && !fields[r - 1][c - 1].isMine())
                revealSurroundingPlots(r - 1, c - 1);
            if (r - 1 >= 0 && fields[r - 1][c].isEnabled() && !fields[r - 1][c].isMine())
                revealSurroundingPlots(r - 1, c);
            if (r - 1 >= 0 && c + 1 <= (minefieldColumns - 1) && fields[r - 1][c + 1].isEnabled() && !fields[r - 1][c + 1].isMine())
                revealSurroundingPlots(r - 1, c + 1);

            if (c - 1 >= 0 && fields[r][c - 1].isEnabled() && !fields[r][c - 1].isMine())
                revealSurroundingPlots(r, c - 1);
            if (c + 1 <= (minefieldColumns - 1) && fields[r][c + 1].isEnabled() && !fields[r][c + 1].isMine())
                revealSurroundingPlots(r, c + 1);

            if (r + 1 <= (minefieldRows - 1) && c - 1 >= 0 && fields[r + 1][c - 1].isEnabled() && !fields[r + 1][c - 1].isMine())
                revealSurroundingPlots(r + 1, c - 1);
            if (r + 1 <= (minefieldRows - 1) && fields[r + 1][c].isEnabled() && !fields[r + 1][c].isMine())
                revealSurroundingPlots(r + 1, c);
            if (r + 1 <= (minefieldRows - 1) && c + 1 <= (minefieldColumns - 1) && fields[r + 1][c + 1].isEnabled() && !fields[r + 1][c + 1].isMine())
                revealSurroundingPlots(r + 1, c + 1);
        }
    }

    public int getMinefieldRows() {
        return minefieldRows;
    }

    public int getMinefieldColumns() {
        return minefieldColumns;
    }

}
