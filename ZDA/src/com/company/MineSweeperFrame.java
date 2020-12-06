package com.company;

import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame {
    public MineSweeperFrame() {

        var frameSize = new Dimension(9*35,9*35+10);
        setSize(frameSize);
        setTitle("MineSweeper");

        add(new GameOptionsPanel(this));
//        add(new MineSweeperPanel());
//        pack();
    }
}
