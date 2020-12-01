package com.company;

import javax.swing.*;

public class MineSweeperFrame extends JFrame {
    public MineSweeperFrame() {
        add(new MineSweeperPanel());
        pack();
    }
}
