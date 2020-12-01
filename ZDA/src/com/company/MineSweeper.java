package com.company;

import javax.swing.*;
import java.awt.*;

public class MineSweeper {

    public static void main(String[] args) {
	EventQueue.invokeLater(() -> {
	    var frame = new MineSweeperFrame();
	    var frameSize = new Dimension(9*35,9*35+10);
	    frame.setSize(frameSize);
	    frame.setTitle("MineSweeper");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
            });
    }
}
