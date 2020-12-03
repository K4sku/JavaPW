package com.company;

import javax.swing.*;
import java.awt.*;

public class MineSweeper {

    public static void main(String[] args) {


	EventQueue.invokeLater(() -> {
	    var frame = new MineSweeperFrame();

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
            });
    }
}
