/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dcrs.gui;

import dcrs.sx.SX;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 * Make a GUI inherit from this instead of JFrame if :
 *  - the application is closed when the GUI is closed
 *  - you want the connection to the railway to be closed when the GUI is closed
 *  - no other GUI inherits from this
 * @author Mathias Seuret
 */
public class RailwayGUIFrame extends JFrame {

    RailwayGUIFrame() {
        this.addWindowListener(
                new WindowListener() {

                    @Override
                    public void windowOpened(WindowEvent we) {
                    }

                    @Override
                    public void windowClosing(WindowEvent we) {
                        System.out.println("Closing port...");
                        SX.instance().closePort();
                        System.out.println("Port closed.");
                    }

                    @Override
                    public void windowClosed(WindowEvent we) {
                    }

                    @Override
                    public void windowIconified(WindowEvent we) {
                    }

                    @Override
                    public void windowDeiconified(WindowEvent we) {
                    }

                    @Override
                    public void windowActivated(WindowEvent we) {
                    }

                    @Override
                    public void windowDeactivated(WindowEvent we) {
                    }
                });
    }
}
