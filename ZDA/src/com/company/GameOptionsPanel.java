package com.company;

import javax.swing.*;
import java.awt.*;

public class GameOptionsPanel extends JPanel {

    private JPanel gameOptionsPanel;
    private GridBagConstraints gbc;

    public GameOptionsPanel(JFrame frame) {
        frame.setSize(new Dimension(123,1111111111));
        frame.repaint();

        gameOptionsPanel = new JPanel();
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor= GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.NONE;

        //Row top
        JLabel header = new JLabel("Game options");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(header, gbc);

        JButton close = new JButton("X");
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        add(close,gbc);

        //row labels
        JLabel height = new JLabel("Height");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(height,gbc);

        JLabel width = new JLabel("Width");
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(width,gbc);

        JLabel mines = new JLabel("Mines");
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(mines,gbc);

        //declare button group
        ButtonGroup gameLevelBG=new ButtonGroup();

        //Row easy
        JRadioButton easy = new JRadioButton("Easy");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gameLevelBG.add(easy);
        add(easy,gbc);

        JLabel easyHeight = new JLabel("9");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(easyHeight,gbc);

        JLabel easyWidth = new JLabel("9");
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(easyWidth,gbc);

        JLabel easyMines = new JLabel("10");
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(easyMines,gbc);

        //Row Medium
        JRadioButton medium = new JRadioButton("Medium");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gameLevelBG.add(medium);
        add(medium,gbc);

        JLabel mediumHeight = new JLabel("16");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(mediumHeight,gbc);

        JLabel mediumWidth = new JLabel("16");
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(mediumWidth,gbc);

        JLabel mediumMines = new JLabel("40");
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(mediumMines,gbc);

        //Row Hard
        JRadioButton hard = new JRadioButton("Hard");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gameLevelBG.add(hard);
        add(hard,gbc);

        JLabel hardHeight = new JLabel("16");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(hardHeight,gbc);

        JLabel hardWidth = new JLabel("30");
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(hardWidth,gbc);

        JLabel hardMines = new JLabel("99");
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(hardMines,gbc);

        //Row custom
        JRadioButton custom = new JRadioButton("Custom");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gameLevelBG.add(custom);
        add(custom,gbc);

        JTextField customHeight = new JTextField("9",2);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(customHeight,gbc);

        JTextField customWidth = new JTextField("9",2);
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(customWidth,gbc);

        JTextField customMines = new JTextField("10",2);
        gbc.gridx = 3;
        gbc.gridy = 5;
        add(customMines,gbc);

        //Last Row
        JButton newGame = new JButton("New game");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(newGame,gbc);
    }
}
