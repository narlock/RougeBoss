package com.narlock.rogue.gui;

import javax.swing.*;

public class Window extends JFrame {

  public GraphicsPanel gp;

  public Window() {
    setTitle("Rogue Boss");
    setSize(300, 300);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    gp = new GraphicsPanel();
    add(gp);

    setLocationRelativeTo(null);
    setVisible(true);
  }
}
