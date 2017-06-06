package BachelorArbeitWs1617;

import animal.exchange.AnimationExporter;
import animal.exchange.AnimationImporter;
import animal.exchange.AnimationPrintJob;
import animal.gui.*;
import animal.main.*;
import animal.misc.MessageDisplay;
import animal.misc.XProperties;
import animal.vhdl.exchange.VHDLAnimationImporter;
import animalscript.core.AnimalScriptParser;
import htdptl.gui.HtDPTLWizard;
import javafx.scene.control.SplitPane;
import translator.AnimalTranslator;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static java.awt.Color.red;


/**
 * Created by deniz on 01.12.16.
 */
public class Gui  {

    static Animation currentAnimation;
    static MyToolBar toolBar;
   static Animal animal;
    static MyMini mAP;

    static String PATH = "/Users/deniz/IdeaProjects/GuiBachelorarbeit/src/BachelorArbeitWs1617/";
   static String demoString =
           "{\n" +
           "  text \"Animation-Name\" \"Datenstruktur: Queue - Enqueue\" (25, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
           "  rectangle \"NamensFeld\" (20, 20) (250, 50) color (0, 0, 0) depth 1\n" +
           "}\n" +
           "{\n" +
           "  codegroup \"sourceCode\" at (20, 200) color (0, 0, 0) highlightColor (255, 0, 0) contextColor (0, 0, 255) font Arial size 12 depth 1\n" +
           "  addCodeLine \"Queue newQueue = new Queue();\" to \"sourceCode\"\n" +
           "  addCodeLine \"\" to \"sourceCode\"\n" +
           "  addCodeLine \".\" to \"sourceCode\"\n" +
           "  addCodeLine \".\" to \"sourceCode\"\n" +
           "  addCodeLine \".\" to \"sourceCode\"\n" +
           "  addCodeLine \"\" to \"sourceCode\"\n" +
           "  addCodeLine \"newQueue.Enqueue( first );\" to \"sourceCode\" indentation 1\n" +
           "  addCodeLine \"newQueue.Enqueue( second );\" to \"sourceCode\" indentation 1\n" +
           "  addCodeLine \"newQueue.Enqueue( third );\" to \"sourceCode\" indentation 1\n" +
           "  addCodeLine \"newQueue.Enqueue( fourth );\" to \"sourceCode\" indentation 1\n" +
           "  addCodeLine \"newQueue.Enqueue( fives );\" to \"sourceCode\" indentation 1\n" +
           "  addCodeLine \"\" to \"sourceCode\" indentation 2\n" +
           "  addCodeLine \".\" to \"sourceCode\"\n" +
           "  addCodeLine \".\" to \"sourceCode\"\n" +
           "  addCodeLine \".\" to \"sourceCode\"\n" +
           "}\n" +
           "{\n" +
           "  highlightCode on \"sourceCode\" line 0 row 0  \n" +
           "}\n" +
           "{\n" +
           "  array \"String Queue\" (20, 140) color (0, 0, 0) fillColor (255, 255, 255) elementColor (0, 0, 0) elemHighlight (255, 0, 0) cellHighlight (255, 255, 0) horizontal length 1 \"\"  depth 1 font SansSerif size 12\n" +
           "  text \"Text1\" \"Size of Queue = 0\" (500, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
           "  arrayMarker \"Front0\" on \"String Queue\" atIndex 0 label \"Back\" color (0, 0, 0) depth 1\n" +
           "}\n" +
           "{\n" +
           "  unhighlightCode on \"sourceCode\" line 0 row 0  \n" +
           "}\n" +
           "{\n" +
           "  highlightCode on \"sourceCode\" line 6 row 0  \n" +
           "}\n" +
           "{\n" +
           "  text \"Text2\" \"Size of Queue = 1\" (500, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
           "  array \"StringArray1\" (20, 140) color (0, 0, 0) fillColor (255, 255, 255) elementColor (0, 0, 0) elemHighlight (255, 0, 0) cellHighlight (255, 255, 0) horizontal length 1 \"first\"  depth 1 font SansSerif size 12\n" +
           "  arrayMarker \"Front\" on \"StringArray1\" atIndex 0 label \"Front\" color (0, 0, 0) depth 1\n" +
           "  hide \"Text1\" \"String Queue\" \n" +
           "}\n" +
           "{\n" +
           "  unhighlightCode on \"sourceCode\" line 6 row 0  \n" +
           "}\n" +
           "{\n" +
           "  highlightCode on \"sourceCode\" line 7 row 0  \n" +
           "}\n" +
           "{\n" +
           "  text \"Text3\" \"Size of Queue = 2\" (500, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
           "  array \"StringArray2\" (20, 140) color (0, 0, 0) fillColor (255, 255, 255) elementColor (0, 0, 0) elemHighlight (255, 0, 0) cellHighlight (255, 255, 0) horizontal length 2 \"second\" \"first\"  depth 1 font SansSerif size 12\n" +
           "  arrayMarker \"ArrayMarker1\" on \"StringArray2\" atIndex 1 label \"Front\" color (0, 0, 0) depth 1\n" +
           "  hide \"Text2\" \"StringArray1\" \n" +
           "}\n" +
           "{\n" +
           "  unhighlightCode on \"sourceCode\" line 7 row 0  \n" +
           "}\n" +
           "{\n" +
           "  highlightCode on \"sourceCode\" line 8 row 0  \n" +
           "}\n" +
           "{\n" +
           "  text \"Text4\" \"Size of Queue = 3\" (500, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
           "  array \"StringArray3\" (20, 140) color (0, 0, 0) fillColor (255, 255, 255) elementColor (0, 0, 0) elemHighlight (255, 0, 0) cellHighlight (255, 255, 0) horizontal length 3 \"third\" \"second\" \"first\"  depth 1 font SansSerif size 12\n" +
           "  arrayMarker \"ArrayMarker2\" on \"StringArray3\" atIndex 2 label \"Front\" color (0, 0, 0) depth 1\n" +
           "  hide \"Text3\" \"StringArray2\" \n" +
           "}\n" +
           "{\n" +
           "  unhighlightCode on \"sourceCode\" line 8 row 0  \n" +
           "}\n" +
           "{\n" +
           "  highlightCode on \"sourceCode\" line 9 row 0  \n" +
           "}\n" +
           "{\n" +
           "  text \"Text5\" \"Size of Queue = 4\" (500, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
           "  array \"StringArray4\" (20, 140) color (0, 0, 0) fillColor (255, 255, 255) elementColor (0, 0, 0) elemHighlight (255, 0, 0) cellHighlight (255, 255, 0) horizontal length 4 \"fourth\" \"third\" \"second\" \"first\"  depth 1 font SansSerif size 12\n" +
           "  arrayMarker \"ArrayMarker3\" on \"StringArray4\" atIndex 3 label \"Front\" color (0, 0, 0) depth 1\n" +
           "  hide \"Text4\" \"StringArray3\" \n" +
           "}\n" +
           "{\n" +
           "  unhighlightCode on \"sourceCode\" line 9 row 0  \n" +
           "}\n" +
           "{\n" +
           "  highlightCode on \"sourceCode\" line 10 row 0  \n" +
           "}\n" +
           "{\n" +
           "  text \"Text6\" \"Size of Queue = 5\" (500, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
           "  array \"StringArray5\" (20, 140) color (0, 0, 0) fillColor (255, 255, 255) elementColor (0, 0, 0) elemHighlight (255, 0, 0) cellHighlight (255, 255, 0) horizontal length 5 \"fives\" \"fourth\" \"third\" \"second\" \"first\"  depth 1 font SansSerif size 12\n" +
           "  arrayMarker \"ArrayMarker4\" on \"StringArray5\" atIndex 4 label \"Front\" color (0, 0, 0) depth 1\n" +
           "  hide \"Text5\" \"StringArray4\" \n" +
           "}\n" +
           "{\n" +
           "  unhighlightCode on \"sourceCode\" line 10 row 0  \n" +
           "}\n" +
           "{\n" +
           "  highlightCode on \"sourceCode\" line 11 row 0  \n" +
           "}\n" +
           "{\n" +
           "  text \"Text7\" \"Size of Queue = 6\" (500, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
           "  array \"StringArray6\" (20, 140) color (0, 0, 0) fillColor (255, 255, 255) elementColor (0, 0, 0) elemHighlight (255, 0, 0) cellHighlight (255, 255, 0) horizontal length 6 \"seventh\" \"fives\" \"fourth\" \"third\" \"second\" \"first\"  depth 1 font SansSerif size 12\n" +
           "  arrayMarker \"ArrayMarker5\" on \"StringArray6\" atIndex 5 label \"Front\" color (0, 0, 0) depth 1\n" +
           "  hide \"Text6\" \"StringArray5\" \n" +
           "}\n" +
           "{\n" +
           "  unhighlightCode on \"sourceCode\" line 11 row 0  \n" +
           "}\n" +
           "{\n" +
           "  highlightCode on \"sourceCode\" line 12 row 0  \n" +
           "}\n" +
           "{\n" +
           "  text \"Text8\" \"Size of Queue = 7\" (500, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
           "  array \"StringArray7\" (20, 140) color (0, 0, 0) fillColor (255, 255, 255) elementColor (0, 0, 0) elemHighlight (255, 0, 0) cellHighlight (255, 255, 0) horizontal length 7 \"eight\" \"seventh\" \"fives\" \"fourth\" \"third\" \"second\" \"first\"  depth 1 font SansSerif size 12\n" +
           "  arrayMarker \"ArrayMarker6\" on \"StringArray7\" atIndex 6 label \"Front\" color (0, 0, 0) depth 1\n" +
           "  hide \"Text7\" \"StringArray6\" \n" +
           "}\n" +
           "{\n" +
           "  unhighlightCode on \"sourceCode\" line 12 row 0  \n" +
           "}\n" +
           "{\n" +
           "}";

    static String demoStringStack = "{\n" +
            "  text \"Animation-Name\" \"%EAX\" (300, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  text \"Text1\" \"%EBX\" (300, 40) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  text \"Text2\" \"\" (305, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  text \"Text3\" \"\" (305, 40) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  text \"Text4\" \"Stack - Arithmetic Expression\" (25, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  rectangle \"NamensFeld\" (20, 20) (200, 50) color (0, 0, 0) depth 1\n" +
            "  text \"Stack-0\" \"\" (305, 256) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  rectangle \"Rect1\" (300, 250) (500, 300) color (0, 0, 0) depth 1\n" +
            "  text \"Stack-1\" \"\" (305, 366) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  rectangle \"Rect2\" (300, 300) (500, 350) color (0, 0, 0) depth 1\n" +
            "  text \"Stack-2\" \"\" (305, 376) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  rectangle \"Rect3\" (300, 350) (500, 400) color (0, 0, 0) depth 1\n" +
            "  text \"Stack-3\" \"\" (305, 386) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  rectangle \"Rect4\" (300, 400) (500, 450) color (0, 0, 0) depth 1\n" +
            "}\n" +
            "{\n" +
            "  codegroup \"sourceCode\" at (20, 200) color (0, 0, 0) highlightColor (255, 0, 0) contextColor (0, 0, 255) font Arial size 12 depth 1\n" +
            "  addCodeLine \"\" to \"sourceCode\"\n" +
            "  addCodeLine \"movl $3, %eax \" to \"sourceCode\"\n" +
            "  addCodeLine \"movl $1, %ebx \" to \"sourceCode\"\n" +
            "  addCodeLine \"subl $ebx, %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"pushl %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"\" to \"sourceCode\"\n" +
            "  addCodeLine \"movl $2, %ebx \" to \"sourceCode\"\n" +
            "  addCodeLine \"pop %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"idiv $ebx\" to \"sourceCode\"\n" +
            "  addCodeLine \"pushl %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"\" to \"sourceCode\"\n" +
            "  addCodeLine \"movl $1, %ebx \" to \"sourceCode\"\n" +
            "  addCodeLine \"pop %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"addl $ebx, %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"pushl %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"\" to \"sourceCode\"\n" +
            "  addCodeLine \"movl $72, %ebx \" to \"sourceCode\"\n" +
            "  addCodeLine \"pop %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"imull %eax, %ebx, %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"pushl %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"\" to \"sourceCode\"\n" +
            "  addCodeLine \"pushl %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"pushl $intout\" to \"sourceCode\"\n" +
            "  addCodeLine \"\" to \"sourceCode\"\n" +
            "  addCodeLine \"call printf \" to \"sourceCode\"\n" +
            "  addCodeLine \"movl $1, %eax\" to \"sourceCode\"\n" +
            "  addCodeLine \"int $0x80\" to \"sourceCode\"\n" +
            "  addCodeLine \"\" to \"sourceCode\"\n" +
            "}\n" +
            "{\n" +
            "  text \"Text5\" \"3\" (350, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  highlightCode on \"sourceCode\" line 0 row 0  \n" +
            "  hide \"Text2\" \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 0 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 1 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text6\" \"1\" (350, 40) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  unhighlightCode on \"sourceCode\" line 1 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 2 row 0  \n" +
            "  hide \"Text3\" \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 2 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 2 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 3 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text7\" \"2\" (350, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  highlightCode on \"sourceCode\" line 3 row 0  \n" +
            "  hide \"Text5\" \n" +
            "}\n" +
            "{\n" +
            "  text \"Text8\" \"2\" (305, 256) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  unhighlightCode on \"sourceCode\" line 3 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 4 row 0  \n" +
            "  hide \"Stack-0\" \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 4 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 4 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 5 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text9\" \"2\" (350, 40) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  unhighlightCode on \"sourceCode\" line 5 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 6 row 0  \n" +
            "  hide \"Text6\" \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 6 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 6 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 7 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text10\" \"2\" (350, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  highlightCode on \"sourceCode\" line 7 row 0  \n" +
            "  hide \"Text7\" \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 7 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 8 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text11\" \"1\" (350, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  highlightCode on \"sourceCode\" line 8 row 0  \n" +
            "  hide \"Text10\" \n" +
            "}\n" +
            "{\n" +
            "  text \"Text12\" \"1\" (305, 256) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  unhighlightCode on \"sourceCode\" line 8 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 9 row 0  \n" +
            "  hide \"Text8\" \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 9 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 9 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 10 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text13\" \"1\" (350, 40) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  unhighlightCode on \"sourceCode\" line 10 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 11 row 0  \n" +
            "  hide \"Text9\" \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 11 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 11 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 12 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text14\" \"1\" (350, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  highlightCode on \"sourceCode\" line 12 row 0  \n" +
            "  hide \"Text11\" \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 12 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 13 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text15\" \"2\" (350, 30) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  highlightCode on \"sourceCode\" line 13 row 0  \n" +
            "  hide \"Text14\" \n" +
            "}\n" +
            "{\n" +
            "  text \"Text16\" \"2\" (305, 256) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  unhighlightCode on \"sourceCode\" line 13 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 14 row 0  \n" +
            "  hide \"Text12\" \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 14 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 14 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 15 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text17\" \"72\" (350, 40) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  unhighlightCode on \"sourceCode\" line 15 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 16 row 0  \n" +
            "  hide \"Text13\" \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 16 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 16 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 17 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 17 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 17 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 18 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 18 row 0  \n" +
            "}\n" +
            "{\n" +
            "  text \"Text18\" \"144\" (305, 256) color (0, 0, 0) depth 1 font SansSerif size 12\n" +
            "  unhighlightCode on \"sourceCode\" line 18 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 19 row 0  \n" +
            "  hide \"Text16\" \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 19 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 19 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 20 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 20 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 20 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 21 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 21 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 21 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 22 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 22 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 22 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 23 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 23 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 23 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 24 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 24 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 24 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 25 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 25 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 25 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 26 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 26 row 0  \n" +
            "}\n" +
            "{\n" +
            "  unhighlightCode on \"sourceCode\" line 26 row 0  \n" +
            "  highlightCode on \"sourceCode\" line 27 row 0  \n" +
            "}\n" +
            "{\n" +
            "  highlightCode on \"sourceCode\" line 27 row 0  \n" +
            "}\n";


    public static List<String> codeA;

    public static void main(String args[]) {

        JFrame fenster = new JFrame();
        initMainFrame(fenster);

        initMyMenu(fenster);
        initCodeArray();

       /* AnimationWindow aw = new AnimationWindow(animal,new XProperties());
        animal.initializeFileChooser();
        animal.openGenerator();
        AnimalMainWindow amw = new AnimalMainWindow(animal, new XProperties(),false,false);*/

        mAP = new MyMini(150, 150);

         toolBar = new MyToolBar(fenster,mAP);

        JPanel retWindow = mAP.getDisplayWindow();
        MyPane splitPane = new MyPane(fenster , mAP.getAC(), retWindow, mAP.getAnim(), mAP ,toolBar,codeA,animal);


    }

    private static void initMyMenu(JFrame fenster) {
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
        open.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    toolBar.openFILE();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }

        });

        JMenuItem save = new JMenuItem("Speichern", KeyEvent.VK_C);
        save.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentAnimation =  mAP.getAnimation();
                if(currentAnimation != null) {
                    AnimationExporter.exportAnimation(currentAnimation);
                }
                else {
                    System.out.print("ERRROOOORRR");
                }
            }
        });

        JMenuItem menuFileExit = new JMenuItem("Beenden");
        menuFileExit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileExitActionPerformed(evt);
            }
        });

        datei.add(open);
        datei.add(save);
        datei.add(menuFileExit);

        // Menu Items for 'ansicht'
        JMenuItem small = new JMenuItem("Klein", KeyEvent.VK_E);
        JMenuItem full = new JMenuItem("Vollansicht", KeyEvent.VK_F);

        ansicht.add(small);
        ansicht.add(full);

        // Menu Items for 'extras'
        JMenuItem tipps = new JMenuItem("TIPPS", KeyEvent.VK_G);
        tipps.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrame newFrame = new JFrame();
                JEditorPane jp = new JEditorPane();
                jp.setBackground(Color.WHITE);
                jp.setSize(200,200);
                jp.setVisible(true);

                JScrollPane scroll=new JScrollPane(jp);
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scroll.setMinimumSize(new Dimension(400,600));

                newFrame.setBackground(red);
                newFrame.setSize(300,400);
                newFrame.setVisible(true);
                newFrame.getContentPane().add(scroll);
                jp.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
                jp.setContentType("text/html");
                jp.setEditable(false);
                File file = new File(PATH+"_Headline-(Abstract_Data_Type).html");
                try{
                    jp.setPage(file.toURI().toURL());
                }catch(MalformedURLException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }


            }
        });

        extras.add(tipps);

        // Menu Items for 'hilfe'
        JMenuItem helpPage = new JMenuItem("Hilfe", KeyEvent.VK_H);

        hilfe.add(helpPage);



        menubar0.add(datei);
        menubar0.add(ansicht);
        menubar0.add(extras);
        menubar0.add(hilfe);


    }


    private static void menuFileExitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private static void initMainFrame(JFrame f) {
        f.setMinimumSize(new Dimension(1200,400));
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


        private static void initCodeArray() {
        codeA = new ArrayList<String>();
        codeA.add(demoString);
        codeA.add(demoStringStack);
    }




    }







