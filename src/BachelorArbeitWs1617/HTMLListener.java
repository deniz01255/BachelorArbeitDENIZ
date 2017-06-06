package BachelorArbeitWs1617;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.io.IOException;

/**
 * Created by deniz on 29.05.17.
 */
public class HTMLListener implements HyperlinkListener {

    JEditorPane edit;

    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            try {
                edit.setPage(e.getURL());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
