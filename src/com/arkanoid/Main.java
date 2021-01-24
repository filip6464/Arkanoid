package com.arkanoid;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Main extends JFrame {

    public Main() {
        init();
    }

    private void init() {
        setTitle("Arkanoid");
        add(new Display());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var window = new Main();
            window.setVisible(true);
        });

    }
}
