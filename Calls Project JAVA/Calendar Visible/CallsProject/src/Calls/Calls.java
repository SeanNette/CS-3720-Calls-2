package Calls;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Interface.Interface;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Wojg
 */
public class Calls {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame("Calls Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Interface inter = new Interface();
        inter.createMainWindow();
        inter.setOpaque(true);
        
        frame.setContentPane(inter);
        frame.getContentPane().setBackground(new Color(102, 102, 102));
        frame.setSize(800, 800);
        frame.setVisible(true);

    }
}