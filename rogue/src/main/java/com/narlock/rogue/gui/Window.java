package com.narlock.rogue.gui;

import javax.swing.*;

public class Window extends JFrame {

  public final String VERSION = "v1.0.0";
  public GraphicsPanel gp;

  public Window() {
    setTitle("Rogue Boss " + VERSION);
    setSize(300, 300);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    gp = new GraphicsPanel();
    add(gp);

    setLocationRelativeTo(null);
    setVisible(true);
  }
}
