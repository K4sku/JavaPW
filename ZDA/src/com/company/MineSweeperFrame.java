package com.company;

import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame {
    public MineSweeperFrame() {

        var frameSize = new Dimension(9*35,9*35+10);
        setSize(frameSize);
        setTitle("MineSweeper");

<<<<<<< Updated upstream
        add(new GameOptionsPanel(this));
//        add(new MineSweeperPanel());
//        pack();
    }
=======
        JPanel gui = new JPanel();
        gui.setLayout(new BorderLayout());
        gui.setEnabled(true);

        var mineLabel = new JLabel(String.valueOf(mines), JLabel.CENTER);
        mineLabel.setEnabled(true);

        var optionMenuButton = new JButton("Game Options");
        var gameOptions = new GameOptions();
        optionMenuButton.addActionListener(gameOptions);
        optionMenuButton.setEnabled(true);

        topBar = new JPanel();
        topBar.add(optionMenuButton);
        topBar.add(Box.createHorizontalGlue());
        topBar.add(mineLabel);
        topBar.add(Box.createHorizontalGlue());
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.LINE_AXIS));

        gui.add(topBar, BorderLayout.NORTH);
        add(gui);

        var mineSweeperPanel = new MineSweeperPanel(9,9,10);
        gui.add(mineSweeperPanel, BorderLayout.CENTER);

        pack();
        repaint();

    }

    private class GameOptions implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String s = event.getActionCommand();
            System.out.println("aaaaaaa");
//        if (s.equals("Game Options")) {
            // create a dialog Box
            JDialog d = new JDialog(frame, "dialog Box");

            // create a label
            JLabel l = new JLabel("this is a dialog box");

            d.add(l);

            // setsize of dialog
            d.setSize(100, 100);

            // set visibility of dialog
            d.setVisible(true);
//        }
        }
    }

>>>>>>> Stashed changes
}
