package BachelorArbeitWs1617;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by deniz on 01.12.16.
 */
public class MyMenu extends JMenuBar {


    public MyMenu(JFrame fenster) {
        JMenuBar menubar0 = new JMenuBar();
        fenster.setJMenuBar(menubar0);

        // THREE menus
        JMenu datei = new JMenu("Datei");
        JMenu ansicht = new JMenu("Ansicht");
        JMenu extras = new JMenu("Extras");
        JMenu hilfe = new JMenu("Hilfe");

        datei.setMnemonic(KeyEvent.VK_W);
        ansicht.setMnemonic(KeyEvent.VK_X);
        extras.setMnemonic(KeyEvent.VK_Y);
        hilfe.setMnemonic(KeyEvent.VK_Z);


        // Menu Items for 'datei'
        JMenuItem open = new JMenuItem("Öffnen", KeyEvent.VK_B);
        JMenuItem save = new JMenuItem("Speichern", KeyEvent.VK_C);
        JMenuItem close = new JMenuItem("Beenden", KeyEvent.VK_D);

        datei.add(open);
        //datei.add(save);
      //  datei.add(close);



        // Menu Items for 'ansicht'
        JMenuItem small = new JMenuItem("Klein", KeyEvent.VK_E);
        JMenuItem full = new JMenuItem("Vollansicht", KeyEvent.VK_F);

        ansicht.add(small);
        ansicht.add(full);

        // Menu Items for 'extras'
        JMenuItem tipps = new JMenuItem("TIPPS", KeyEvent.VK_G);

        extras.add(tipps);

        // Menu Items for 'hilfe'
        JMenuItem helpPage = new JMenuItem("Hilfe", KeyEvent.VK_H);

        hilfe.add(helpPage);



        menubar0.add(datei);
        menubar0.add(ansicht);
        menubar0.add(extras);
        menubar0.add(hilfe);

    }

}
