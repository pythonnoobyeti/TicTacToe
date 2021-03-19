package XO.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JFrame {
    GameWindow(){
        //Create frame
        JFrame frame = new JFrame();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TIC TAC TOE");
        setSize(600,600);
        //Create map panel
        Map map = new Map();
        map.setVisible(false);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));

        //Create buttons
        JButton startButton = new JButton("Start!");
        startButton.setPreferredSize(new Dimension(100,40));
        startButton.setBackground(Color.getHSBColor(0.5f, 3, 0.5f));
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                buttonsPanel.setVisible(false);
                map.makeMapMassive();
                map.setVisible(true);
            }
        });

        JButton exitButton = new JButton("Exit!");
        exitButton.setBackground(Color.getHSBColor(3f, 1.5f, 3));
        exitButton.setPreferredSize(new Dimension(100,40));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.exit(0);
            }
        });

        buttonsPanel.add(startButton);
        buttonsPanel.add(exitButton);

        //Set layout and add into frame
        setLayout(new BorderLayout());
        add(buttonsPanel,BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }

}


