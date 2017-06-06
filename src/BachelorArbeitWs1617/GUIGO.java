package BachelorArbeitWs1617;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class GUIGO extends JFrame {
    private String PATH = "/Users/deniz/IdeaProjects/GuiBachelorarbeit/src/BachelorArbeitWs1617/";
    JScrollPane scroll;
    JEditorPane editorpane=new JEditorPane();
    //this is the constructor
    GUIGO(){
        JFrame frame=new JFrame("Frame");

        frame.add(editorpane);
        scroll=new JScrollPane(editorpane);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setMinimumSize(new Dimension(400,600));
        editorpane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        editorpane.setContentType("text/html");
        editorpane.setEditable(false);
        File file = new File(PATH+"_Headline-(Abstract_Data_Type).html");
        try{
            editorpane.setPage(file.toURI().toURL());
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        editorpane.setText("<html><body>Test <a href='http://www.java.net'>"
                + "www.java.net</a></body></html>");
        StyleSheet css = ((HTMLEditorKit)
                editorpane.getEditorKit()).getStyleSheet();
        Style style = css.getStyle("body");
        editorpane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() ==
                        HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        editorpane.setPage(e.getURL());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    public void setWindow() throws MalformedURLException {
        File file = new File(PATH+"_Headline-(Abstract_Data_Type).html");
        try {
            editorpane.setPage(file.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JScrollPane getEditorpane(){
        return scroll;
    }

    public JEditorPane getMainWindow(){return editorpane;}

    public void append(String s) {
        try{
            Document doc = editorpane.getDocument();
            doc.insertString(doc.getLength(), "\n", null);
            doc.insertString(doc.getLength(), s, null);

        }
        catch(BadLocationException exc){
        }
    }
    //main method
    public static void main(String args[]){

        GUIGO gui=new GUIGO();
        gui.append("<html><body>Test <a href='http://www.java.net'>"
                + "www.java.net</a></body></html>");


    }
}