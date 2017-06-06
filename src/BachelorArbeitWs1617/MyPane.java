package BachelorArbeitWs1617;

import algoanim.primitives.generators.AnimationType;
import algoanim.primitives.generators.Language;
import algoanim.properties.AnimationPropertiesKeys;
import algoanim.properties.TextProperties;
import algoanim.util.Coordinates;
import animal.gui.AnimationDisplayToolBar;
import animal.main.*;
import animal.misc.XProperties;
import animalscript.core.AnimalScriptParser;
import animalscript.core.BasicParser;
import animalscript.core.BasicParser;
import javax.swing.JFileChooser;
import java.io.File;


import javax.imageio.ImageIO;

import java.awt.*;

import java.io.IOException;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

/**
 * Created by deniz on 01.12.16.
 */
public class MyPane extends JPanel implements ActionListener {

    static javax.swing.JPanel jPanel1;
    static javax.swing.JScrollPane jScrollPane1;
    static javax.swing.JSlider jSlider1;
    private ScrollPane sp;
    static ScrollPane sp1;
    static int code =0;
    private QueueAnimSCode s;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    String expression;
    private JSplitPane spRTOP;
    private  JSplitPane spRight;
    private JSplitPane spRealRight;
    private JSplitPane split;
    private AbstractButton scriptingInputOK;

    private int positionCounterQueue;
    private List<String> buffer;
    private int countQueue;
    private int countStack;
    public CircularAnimSCode cASC;

    private String currentAnimalScriptCode = "";


    public ScrollPane sc;
    private String PATH = "/Users/deniz/IdeaProjects/GuiBachelorarbeit/src/BachelorArbeitWs1617/";
    private JPanel upperHalf;
    private JPanel frontChoose;
    private AnimationDisplayToolBar displayControlToolBar;

    JPanel lowerHalf = null;
    JPanel lowerDisplayHalf = null;
    JPanel upperDisplayHalf = null;
    JPanel top = null;

    JButton buttonPlus, buttonMinus;
    double factor = 1.0;

    public MyPane(JFrame fenster , AnimationCanvas ac, JPanel jp, Animal animal, MyMini mM, MyToolBar tB, List<String> l,Animal anim){



        buffer = new ArrayList<String>(){};
        expression = new String("");
        countQueue = 4;
        countStack = 4;
        if (upperHalf == null) {
            frontChoose = new JPanel();
            upperHalf = new JPanel();
            upperHalf.setMinimumSize(new Dimension(500,900));
            addLabels(mM,tB,fenster,l);
        }

        if (lowerHalf == null) {
            lowerHalf = jp;

        if (lowerDisplayHalf == null){
            lowerDisplayHalf = new JPanel();
            lowerDisplayHalf.setSize(150,100);
            lowerDisplayHalf.setBackground(Color.white);
            lowerDisplayHalf.setVisible(true);
        }
            if (upperDisplayHalf == null){
                upperDisplayHalf = new JPanel();
                upperDisplayHalf.setVisible(true);
            }


        if (top == null){
            top = new JPanel();
            top.setVisible(true);
        }

        }
        buttonMinus = new JButton(" - ");
        buttonMinus.setToolTipText("Zoom out of Animation");
        buttonPlus = new JButton(" + ");
        buttonPlus.setToolTipText("Zoom into the Animation");
        buttonMinus.addActionListener(this);
        buttonPlus.addActionListener(this);
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(buttonMinus);
        box.add(Box.createHorizontalStrut(20));
        box.add(buttonPlus);
        box.add(Box.createHorizontalGlue());
        box.setBorder(BorderFactory.createTitledBorder("Zoom: out  --   in"));
        box.setMinimumSize(new Dimension(100,200));
        upperDisplayHalf.add(box);
        upperDisplayHalf.setMinimumSize(new Dimension(100,60));


        //this.displayControlToolBar = new MyAnimationDisplayToolBar("Animation Display Controls", null, animal );
      // top.add(this.displayControlToolBar);

       //  sc = new ScrollPane();
        // #################



        //##################
        sc = new ScrollPane();
        sc.add(upperHalf);
        sc.setSize(150,150);
        sc.setVisible(true);
        sc.revalidate();
        sc.repaint();


        sp1 = new ScrollPane();
        sp1.setWheelScrollingEnabled(true);
        sp1.setMinimumSize(new Dimension(400,400));

        tB.setMYPane(upperHalf,sp1);
        
        frontChoose.setBackground(Color.WHITE);
        setThePane(frontChoose);



      //  sc.add(this.displayControlToolBar);








        JEditorPane editorpane= new JEditorPane();
        JScrollPane jScrollPane = new JScrollPane(editorpane);









        JButton b1 = new JButton("Nur ein Test");




        //#######################

        boolean isNeedCursorChange=true;
        JTextPane edit=new JTextPane() {
            public void setCursor(Cursor cursor) {
                if (isNeedCursorChange) {
                    super.setCursor(cursor);
                }
            }
        };



        //######################




        // Create the Right pane


        spRTOP = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperDisplayHalf, sp1); // sc
        spRTOP.setDividerSize(8);
        spRTOP.setResizeWeight(0.5);
        spRTOP.setContinuousLayout(true);
        spRTOP.setBorder(BorderFactory.createTitledBorder("AnimationWindow"));
        spRTOP.setVisible(true);
        spRTOP.setOneTouchExpandable(true);


        lowerHalf.setMinimumSize(new Dimension(600,30));
        lowerHalf.setMaximumSize(new Dimension(1800,30));

        spRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, spRTOP, lowerHalf); // sc
        spRight.setDividerSize(8);
        spRight.setResizeWeight(0.5);
        spRight.setContinuousLayout(true);
        spRight.setVisible(true);
        spRight.setOneTouchExpandable(true);


        spRealRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, spRight, lowerDisplayHalf);
        spRealRight.setDividerSize(8);
        spRealRight.setResizeWeight(0.5);
        spRealRight.setContinuousLayout(true);
        spRealRight.setVisible(true);
        spRealRight.setOneTouchExpandable(true);

        GUIGO gg = new GUIGO();

        tB.setMainWindow(gg);
        gg.setMinimumSize(new Dimension(400,600));
        spRealRight.setMinimumSize(new Dimension(400,600));

        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gg.getEditorpane() , spRealRight);
        //linkeSeite.getEdit().setEditable(false);
        split.setContinuousLayout(true);
        split.setResizeWeight(0.5);
        split.setOneTouchExpandable(true);
        split.setVisible(true);
        split.setOneTouchExpandable(true);

        fenster.getContentPane().add(split);
        //fenster.add(this.displayControlToolBar);
        fenster.revalidate();
        fenster.pack();
        fenster.setVisible(true);



    }

    private void addLabels(MyMini mM, MyToolBar tB, JFrame fenster,List<String> l) {


        JLabel textAbove = new JLabel("Queue Animation");
        JButton btnStart = new JButton();
        btnStart.setToolTipText("Start Animation of Queue");

        JLabel textAbove1 = new JLabel("Stack Animation");
        JButton btnStart1 = new JButton();
        btnStart1.setToolTipText("Start Animation of Stack");

        JLabel textAbove2 = new JLabel("RingBuffer Animation");
        JButton btnStart2 = new JButton();
        btnStart2.setToolTipText("Start Animation of Ringbuffer");
        
        tB.setMyp(this);

        btnStart1.add(textAbove1);
        btnStart1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mM.setCurAnimNumber(1);

                code = 1;
                upperHalf.removeAll();
                upperHalf = mM.getAC();

                //upperHalf=  mM.getAnim().getMainWindow().getWindowCoordinator().getAnimationWindow(true).getAnimationCanvas();
                upperHalf.revalidate();
                sp1.removeAll();
                setThePane(upperHalf);
                sp1.add(upperHalf);
                upperHalf.repaint();
                sp1.repaint();

                System.out.print("HERE");

                currentAnimalScriptCode = generateStackCode(fenster,true);
                mM.setAnimalScript(currentAnimalScriptCode);
                mM.setAnimationCode(currentAnimalScriptCode);
                mM.runDemo();

                setToolBarForQueue(tB,fenster,"Stack",mM,l);

            }
        });
        btnStart1.setBounds(500, 500, 500, 500);
        frontChoose.add(btnStart1);


        btnStart2.add(textAbove2);
        btnStart2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mM.setCurAnimNumber(2);
                code = 2;

                upperHalf.removeAll();
                upperHalf = mM.getAC();
                //upperHalf.revalidate();
                sp1.removeAll();
                setThePane(upperHalf);
                //  sp1.add(upperHalf);
                upperHalf.repaint();
                cASC = null;
                currentAnimalScriptCode = generateCodeRINGBuffer(fenster, true);
                mM.setAnimalScript(currentAnimalScriptCode);
                System.out.print("HERE");
                mM.setAnimationCode(currentAnimalScriptCode);
                mM.runDemo();
                setAnimationStart(5,mM);
                setToolBarForQueue(tB,fenster,"Ringbuffer",mM,l);

            }
        });
        btnStart2.setBounds(200, 200, 200, 200);
        frontChoose.add(btnStart2);



        btnStart.add(textAbove);
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mM.setCurAnimNumber(3);
                code = 3;


                    upperHalf.removeAll();

                    upperHalf = mM.getAC();
                    sp1.removeAll();
                    setThePane(upperHalf);
                    //  sp1.add(upperHalf);
                    upperHalf.repaint();
                    //sp1.repaint();
                    System.out.print("HERE");
                    currentAnimalScriptCode = generateCode(fenster, true);
                    mM.setAnimalScript(currentAnimalScriptCode);
                    mM.setAnimationCode(currentAnimalScriptCode);
                    mM.runDemo();
                    setToolBarForQueue(tB, fenster, "Queue", mM, l);
                }

        });
        btnStart.setBounds(200, 200, 200, 200);
        frontChoose.setLayout(new BoxLayout(frontChoose, BoxLayout.PAGE_AXIS));
        frontChoose.setBorder(BorderFactory.createEmptyBorder(50,20,20,50));
        frontChoose.add(btnStart);
    }

  /*  private void goDispl() {


        if ( != null) {
            long timeNow = System.currentTimeMillis();

            scriptParser = new AnimalScriptParser();
            // new code
            scriptParser.generateStreamTokenizer(lastScriptingInput, false);
            Animation tmpAnim = scriptParser.importAnimationFrom(new StringReader(lastScriptingInput), true);
            // end new code
//				Animation tmpAnim = scriptParser.programImport(
//						lastScriptingInput, false);

            if (timeNow > System.currentTimeMillis()) {
                timeNow -= System.currentTimeMillis();
            } else {
                timeNow = System.currentTimeMillis() - timeNow;
            }

            if (animalInstance.setAnimation(tmpAnim)) {
               /* animalInstance.getAnimation().resetChange();
                MessageDisplay.message("parsedInternalScripting",
                        Integer.valueOf((int)timeNow));
                animalInstance.setFilename("localBuffer");

                AnimalMainWindow.getWindowCoordinator().getDrawWindow(false).setTitle(
                        "Draw Window - "
                                +AnimalConfiguration.getDefaultConfiguration().getCurrentFilename());
                if (e.getSource() == scriptingInputOK)
                    scriptingInputFrame.setVisible(false);
            }
        }
    }*/

    public String generateStackCode(JFrame fenster, boolean first) {
        if (!(first)) {
             expression = JOptionPane.showInputDialog(fenster,
                    "Please add a complete expression for a new animation !", null);



        }else if(first)
        {
            expression = "((((3-1)/2)+1)*72)";
        }

        String code = animationStackGenerator(expression);
        return code;

    }

    private String animationStackGenerator(String expression) {

        Language l = Language.getLanguageInstance(AnimationType.ANIMALSCRIPT,
                "Stack Arithmetic", "Deniz Can Franz  Ertan", 640, 480);

        StackAnimSCode s = new StackAnimSCode(l);
        try {
            s.parse(expression);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(l);
        System.out.println(l.toString().substring(l.toString().indexOf("{")+1));
        return l.toString().substring(l.toString().indexOf("{")+1);
    }
    /**
     * creates a buffer with input
     * beschränkung der elemente auf 50
     * @param fenster
     * @return
     */
    private String generateCode(JFrame fenster,boolean first) {

        if (!(first)) {

            JLabel lbl = new JLabel(new ImageIcon("_001_QueuePicture.png"));
            String input = JOptionPane.showInputDialog(fenster,
                    "Starting with an empty Queue. Please Type a name to add an element to Empty Queue", "Add first element"); // Please add a name for a further element!
            System.out.println("Old Queue size: "+buffer.size()+" elements");
            buffer.add(input);
            System.out.println("A further element has been added to your Queue : "+input+"/n"+"New Queue size: "+buffer.size()+" elements");



        }else if(first)
                {
                 buffer.add("first");
                    buffer.add("second");
                    buffer.add("third");
                    buffer.add("fourth");
                    buffer.add("fifth");
                    positionCounterQueue = 2;
                }

        String code = animationGenerator(buffer);
        return code;
    }

    private String generateCodeRINGBuffer(JFrame fenster,boolean first) {

        if (!(first)) {

            JLabel lbl = new JLabel(new ImageIcon("_001_QueuePicture.png"));
            String input = JOptionPane.showInputDialog(fenster,
                    "Starting with an empty Queue. Please Type a name to add an element to Empty Queue", "Add first element"); // Please add a name for a further element!
            System.out.println("Old Queue size: "+buffer.size()+" elements");
            cASC.write(input);
            System.out.println("A further element has been added to your Queue : "+input+"/n"+"New Queue size: "+buffer.size()+" elements");



        }else if(first)
        {
            buffer.add("first");
            buffer.add("second");
            buffer.add("third");

           /** cASC.write("first");
            cASC.write("second");
            cASC.write("third");**/

        }

        String code = ringGenerator(buffer);
        return code;
    }


    private String generateRINGBCode(JFrame fenster,boolean first) {

        if (!(first)) {

            JLabel lbl = new JLabel(new ImageIcon("_001_QueuePicture.png"));
            String input = JOptionPane.showInputDialog(fenster,
                    "Starting with an empty Queue. Please Type a name to add an element to Empty Queue", "Add first element"); // Please add a name for a further element!
            System.out.println("Old Queue size: "+buffer.size()+" elements");
            buffer.add(input);
            System.out.println("A further element has been added to your Queue : "+input+"/n"+"New Queue size: "+buffer.size()+" elements");



        }else if(first)
        {
            buffer.add("first");
            buffer.add("second");
            buffer.add("third");
            buffer.add("fourth");
            buffer.add("fifth");
            positionCounterQueue = 2;
        }

        String code = ringGenerator(buffer);
        return code;
    }

    //TODOOOO
    private String animationGenerator(List<String> buffer) {
        Language lang = Language.getLanguageInstance(AnimationType.ANIMALSCRIPT,
                "Queue - Enqueue", "Deniz Can Franz  Ertan", 640, 480);
        QueueAnimSCode ws = new QueueAnimSCode(lang);
        String[] a = {""};
        ws.createAnimationQ(a, buffer);
        System.out.println(lang);
        System.out.println(lang.toString().substring(lang.toString().indexOf("{")+1));
        return lang.toString().substring(lang.toString().indexOf("{")+1);

    }

    private String ringGenerator(List<String> buffer) {
        if (cASC == null) {
            Language lang = Language.getLanguageInstance(AnimationType.ANIMALSCRIPT,
                    "Queue - Enqueue", "Deniz Can Franz  Ertan", 640, 480);
            cASC = new CircularAnimSCode(lang);
            String[] a = {""};
            cASC.createAnimation(a, buffer);
        }
        System.out.println(cASC.getLang());
        System.out.println(cASC.getLang().toString().substring(cASC.getLang().toString().indexOf("{")+1));
        return cASC.getLang().toString().substring(cASC.getLang().toString().indexOf("{")+1);

    }

    /*
    Missing ringbuffer !!!!!
     */
    public void
    setToolBarForQueue(MyToolBar tB,JFrame fenster, String which, MyMini myMini, List<String> ll) {
        if (which.equals("Queue")) {

            JButton addElementButton = new JButton();
            addElementButton.setToolTipText("Add a further element to actuall Queue (ENQUEUE)");
            JButton removeElementButton = new JButton();
            removeElementButton.setToolTipText("Remove an element from  actuall Queue (DEQUEUE)");
            JButton getBackButton = new JButton();
            getBackButton.setToolTipText("Get Back to Main Window");
            try {
                Image fileOpenIcon = ImageIO.read(getClass().getResource("_007_pluszeichen.jpg"));
                BufferedImage oBI = tB.createResizedCopy(fileOpenIcon, 30, 30, false);
                addElementButton.setIcon(new ImageIcon(oBI));


                Image fileSaveIcon = ImageIO.read(getClass().getResource("_006_minus.png"));
                BufferedImage sBI = tB.createResizedCopy(fileSaveIcon, 30, 30, false);
                removeElementButton.setIcon(new ImageIcon(sBI));

                Image fileBack = ImageIO.read(getClass().getResource("_010_BackButton.png"));
                BufferedImage sB = tB.createResizedCopy(fileBack, 30, 30, false);
                getBackButton.setIcon(new ImageIcon(sB));

            } catch (IOException e) {
                e.printStackTrace();
            }

            addElementButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String animationCode = generateCode(fenster,false);
                    int currentStep = myMini.controller.getAnimationState().getStep();
                    int maxStep = myMini.controller.getAnimationState().getLastStep();
                    String secondPart = animationCode;
                    AnimalScriptParser parser = myMini.getParser(false);
                    parser.generateStreamTokenizer(secondPart, false);
                    Animation anim2 = null;

                    try {
                        anim2 = parser.programImport(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    anim2.resetChange();
                    while (countQueue != maxStep){
                        myMini.getAnimation().deleteStep(countQueue);
                        countQueue++;
                    }
                    myMini.dumpAnimation(myMini.controller.getAnimation());
                    myMini.getAnim().getContentPane().removeAll();
                   // myMini.controller.setAnimation(anim2);
                    myMini.setAnimation(anim2);
                    myMini.getAnim().revalidate();
                    setAnimationStart(myMini.controller.getAnimationState().getLastStep(),myMini);
                    System.out.println(myMini.controller.getAnimationState().getStep());
                    System.out.println(myMini.controller.getAnimationState().getLastStep());
                }
            });

            removeElementButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String animationCode = generateCodeRemove(fenster,false);
                    int currentStep = myMini.controller.getAnimationState().getStep();
                    int maxStep = myMini.controller.getAnimationState().getLastStep();
                    String secondPart = animationCode;
                    AnimalScriptParser parser = myMini.getParser(false);
                    parser.generateStreamTokenizer(secondPart, false);
                    Animation anim2 = null;

                    try {
                        anim2 = parser.programImport(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    anim2.resetChange();
                    while (countQueue != maxStep){
                        myMini.getAnimation().deleteStep(countQueue);
                        countQueue++;
                    }
                    myMini.dumpAnimation(myMini.controller.getAnimation());
                    myMini.getAnim().getContentPane().removeAll();
                    // myMini.controller.setAnimation(anim2);
                    myMini.setAnimation(anim2);
                    myMini.getAnim().revalidate();
                    setAnimationStart(myMini.controller.getAnimationState().getLastStep(),myMini);
                    System.out.println(myMini.controller.getAnimationState().getStep());
                    System.out.println(myMini.controller.getAnimationState().getLastStep());

                }
            });
            getBackButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        setThePane(frontChoose);
                        tB.toolbar.remove(addElementButton);
                        tB.toolbar.remove(removeElementButton);
                        tB.toolbar.remove(getBackButton);
                        tB.toolbar.revalidate();
                        tB.toolbar.repaint();
                        fenster.getContentPane().add(tB.toolbar, BorderLayout.NORTH);
                        fenster.revalidate();
                        fenster.repaint();
                        BasicParser.stok = null;
                }
            });

            tB.toolbar.add(addElementButton);
            tB.toolbar.add(removeElementButton);
            tB.toolbar.add(getBackButton);
            tB.toolbar.revalidate();
            tB.toolbar.repaint();

            fenster.getContentPane().add(tB.toolbar, BorderLayout.NORTH);
            fenster.revalidate();
            fenster.repaint();


        } else if (which.equals("Stack")) {
            JButton push = new JButton();
            push.setToolTipText("Add a new Arithmetic expression");
            JButton pop = new JButton();
            pop.setToolTipText("Remove the element from top of Stack");
            JButton getBackButton = new JButton();
            getBackButton.setToolTipText("Get Back to Main Window");
            try {
                Image fileOpenIcon = ImageIO.read(getClass().getResource("_008_PfeilU.png"));
                BufferedImage oBI = tB.createResizedCopy(fileOpenIcon, 30, 30, false);
                push.setIcon(new ImageIcon(oBI));


                Image fileSaveIcon = ImageIO.read(getClass().getResource("_009_PfeilO.jpg"));
                BufferedImage sBI = tB.createResizedCopy(fileSaveIcon, 30, 30, false);
                pop.setIcon(new ImageIcon(sBI));

                Image fileBack = ImageIO.read(getClass().getResource("_010_BackButton.png"));
                BufferedImage sB = tB.createResizedCopy(fileSaveIcon, 30, 30, false);
                getBackButton.setIcon(new ImageIcon(sB));

            } catch (IOException e) {
                e.printStackTrace();
            }

            push.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int currentStep = myMini.controller.getAnimationState().getStep();
                    int maxStep = myMini.controller.getAnimationState().getLastStep();
                    String animationCode = generateStackCode(fenster,false);
                    String secondPart = animationCode;
                    AnimalScriptParser parser = myMini.getParser(false);
                    parser.generateStreamTokenizer(secondPart, false);
                    Animation anim2 = null;

                    try {
                        anim2 = parser.programImport(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    anim2.resetChange();
                    while (countStack != maxStep){
                        myMini.getAnimation().deleteStep(countStack);
                        countStack++;
                    }
                    myMini.dumpAnimation(myMini.controller.getAnimation());
                    myMini.controller.setAnimation(anim2);
                    // myMini.controller.setAnimation(anim2);
                    myMini.setAnimation(anim2);
                    myMini.controller.gotoFrame(maxStep+1);
                    myMini.getAnim().revalidate();
                    System.out.println(myMini.controller.getAnimationState().getStep());
                    System.out.println(myMini.controller.getAnimationState().getLastStep());
                }
            });

            pop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                }
            });

            getBackButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setThePane(frontChoose);
                    tB.toolbar.remove(push);
                    tB.toolbar.remove(pop);
                    tB.toolbar.remove(getBackButton);
                    tB.toolbar.revalidate();
                    tB.toolbar.repaint();
                    fenster.getContentPane().add(tB.toolbar, BorderLayout.NORTH);
                    fenster.revalidate();
                    fenster.repaint();
                    BasicParser.stok = null;

                }
            });

            tB.toolbar.add(push);
            tB.toolbar.add(pop);
            tB.toolbar.add(getBackButton);
            tB.toolbar.revalidate();
            tB.toolbar.repaint();
            fenster.getContentPane().add(tB.toolbar, BorderLayout.NORTH);
            fenster.revalidate();
            fenster.repaint();

        }else if (which.equals("Ringbuffer")) {

            JButton addElementButton = new JButton();
            addElementButton.setToolTipText("Write Element into RingBuffer");
            JButton removeElementButton = new JButton();
            removeElementButton.setToolTipText("Read Element from RingBuffer");
            JButton getBackButton = new JButton();
            getBackButton.setToolTipText("Get Back to Main Window");
            try {
                Image fileOpenIcon = ImageIO.read(getClass().getResource("_007_pluszeichen.jpg"));
                BufferedImage oBI = tB.createResizedCopy(fileOpenIcon, 30, 30, false);
                addElementButton.setIcon(new ImageIcon(oBI));


                Image fileSaveIcon = ImageIO.read(getClass().getResource("_006_minus.png"));
                BufferedImage sBI = tB.createResizedCopy(fileSaveIcon, 30, 30, false);
                removeElementButton.setIcon(new ImageIcon(sBI));

                Image fileBack = ImageIO.read(getClass().getResource("_010_BackButton.png"));
                BufferedImage sB = tB.createResizedCopy(fileBack, 30, 30, false);
                getBackButton.setIcon(new ImageIcon(sB));

            } catch (IOException e) {
                e.printStackTrace();
            }

            addElementButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String animationCode = generateCodeRINGBuffer(fenster,false);
                    int currentStep = myMini.controller.getAnimationState().getStep();
                    int maxStep = myMini.controller.getAnimationState().getLastStep();
                    String secondPart = animationCode;

                    AnimalScriptParser parser = myMini.getParser(false);
                    parser.generateStreamTokenizer(secondPart, false);
                    Animation anim2 = null;

                    try {
                        anim2 = parser.programImport(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    anim2.resetChange();
                    while (countQueue != maxStep){
                        myMini.getAnimation().deleteStep(countQueue);
                        countQueue++;
                    }
                    myMini.dumpAnimation(myMini.controller.getAnimation());
                    myMini.getAnim().getContentPane().removeAll();
                    // myMini.controller.setAnimation(anim2);
                    myMini.setAnimation(anim2);
                    myMini.getAnim().revalidate();
                    setAnimationStart((myMini.controller.getAnimationState().getLastStep())-15,myMini);
                   // setAnimationStart((currentStep*2),myMini);
                    System.out.println(myMini.controller.getAnimationState().getStep());
                    System.out.println(myMini.controller.getAnimationState().getLastStep());
                }
            });

            removeElementButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {



                    String animationCode = generateCodeRINGREAD();
                    int currentStep = myMini.controller.getAnimationState().getStep();
                    int maxStep = myMini.controller.getAnimationState().getLastStep();
                    String secondPart = animationCode;

                    AnimalScriptParser parser = myMini.getParser(false);
                    parser.generateStreamTokenizer(secondPart, false);
                    Animation anim2 = null;

                    try {
                        anim2 = parser.programImport(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    anim2.resetChange();
                    while (countQueue != maxStep){
                        myMini.getAnimation().deleteStep(countQueue);
                        countQueue++;
                    }
                    myMini.dumpAnimation(myMini.controller.getAnimation());
                    myMini.getAnim().getContentPane().removeAll();
                    // myMini.controller.setAnimation(anim2);
                    myMini.setAnimation(anim2);
                    myMini.getAnim().revalidate();
                    if (cASC.codeRAdd){
                        setAnimationStart((myMini.controller.getAnimationState().getLastStep())-20,myMini);
                        cASC.codeRAdd = false;
                    }else{
                        setAnimationStart(myMini.controller.getAnimationState().getLastStep(),myMini);
                    }

                    System.out.println(myMini.controller.getAnimationState().getStep());
                    System.out.println(myMini.controller.getAnimationState().getLastStep());


                }
            });
            getBackButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setThePane(frontChoose);
                    tB.toolbar.remove(addElementButton);
                    tB.toolbar.remove(removeElementButton);
                    tB.toolbar.remove(getBackButton);
                    tB.toolbar.revalidate();
                    tB.toolbar.repaint();
                    fenster.getContentPane().add(tB.toolbar, BorderLayout.NORTH);
                    fenster.revalidate();
                    fenster.repaint();
                    BasicParser.stok = null;
                }
            });

            tB.toolbar.add(addElementButton);
            tB.toolbar.add(removeElementButton);
            tB.toolbar.add(getBackButton);
            tB.toolbar.revalidate();
            tB.toolbar.repaint();

            fenster.getContentPane().add(tB.toolbar, BorderLayout.NORTH);
            fenster.revalidate();
            fenster.repaint();


        }


    }

    private String generateCodeRemove(JFrame fenster, boolean b) {


        buffer.remove(0);
        String code = animationGenerator(buffer);
        return code;
    }

    private String generateCodeRINGREAD() {
        cASC.read();
        String code = ringGenerator(buffer);
        return code;

    }


    private void openAddInputPanel(JFrame fenster,MyMini myMini) {
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());


    }

    public int getCode(){return code;}


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonPlus) {



            factor += 0.03;
        }
        else {
            factor -= 0.03;
        }
        buttonMinus.setEnabled(factor > 0.1);
        buttonPlus.setEnabled(factor < 4.0);
        AnimationCanvas acc= (AnimationCanvas)upperHalf;
        acc.setMagnification(factor);
        upperHalf = acc;
        upperHalf.repaint();
    }

    public static void loadFile(String path, JEditorPane pane) throws IOException{
        File file = new File(path);
        pane.setPage(file.toURI().toURL());

    }

    public void setWindowFRONT(JPanel in){
        sp1.removeAll();
        sp1.add(in);
        sp1.revalidate();
        sp1.repaint();
    }

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {
        sc.repaint();
    }

    void setProperties(XProperties properties) {
        this.setBounds(properties.getIntProperty("animationWindow.x", 50), properties.getIntProperty("animationWindow.y", 50), properties.getIntProperty("animationWindow.width", 400), properties.getIntProperty("animationWindow.height", 200));
    }

    public void setUpper(MyCanvas ac){upperHalf = ac;}

    public void setAnimationStart(int animationStart, MyMini mM) {
        AnimationState currentStep = mM.controller.getAnimationState();
        System.out.print(currentStep.getStep()+"\n");
        mM.controller.gotoFrame(animationStart);
        currentStep = mM.controller.getAnimationState();
        System.out.print(currentStep.getStep());
    }



    public void setThePane(JPanel thePane) {
       // ScrollPane spH = new ScrollPane();
        sp1.setWheelScrollingEnabled(true);
        sp1.removeAll();
        sp1.revalidate();
        sp1.add(thePane);
        sp1.revalidate();
        sp1.repaint();
        //sp1 = spH;

    }
}
/*                    Language l = Language.getLanguageInstance(AnimationType.ANIMALSCRIPT,
                            "Queue - Enqueue", "Deniz Can Franz  Ertan", 640, 480);
                    s = new QueueAnimSCode(l);
                String[] a = {""};
                //openAddInputPanel(fenster,myMini);
                String[] geber = {"first", "second", "third", "fourth", "fives" ,"NEUDUMMY"};
                    buffer = geber;
                s.createAnimationQ(a, buffer);
                    //###################################
                    upperHalf.removeAll();
                    upperHalf = myMini.getAC();
                    //upperHalf.revalidate();
                    sp1.removeAll();
                    sp1.add(upperHalf);
                    upperHalf.repaint();
                    sp1.repaint();
                    //#################################
                    String getter = l.toString();
                    System.out.print(getter);
                    myMini.setAnimationCode(ll.get(1));
                    myMini.runDemo();*/