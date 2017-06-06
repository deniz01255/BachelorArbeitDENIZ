package BachelorArbeitWs1617;

import animal.gui.AnimalMainWindow;
import animal.gui.AnimationControlToolBarController;
import animal.gui.MiniAnimationPlayer;
import animal.main.*;
import animalscript.core.AnimalScriptParser;
import animalscript.core.BasicParser;
import translator.Translator;
import animal.gui.AnimalMainWindow;
import animal.main.Animal;
import animal.main.AnimalConfiguration;
import animal.main.Animation;
import animal.main.AnimationCanvas;
import animal.main.AnimationController;
import animal.main.AnimationListEntry;
import animal.main.AnimationState;
import animalscript.core.AnimalScriptParser;
import animalscript.core.BasicParser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Locale;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import translator.Translator;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Locale;



/**
 * Created by deniz on 21.03.17.
 */
public class MyMini extends MiniAnimationPlayer {
    String demoString;
    Animal anim;
    AnimalConfiguration animalConfig;
    Animation animation;
    AnimationCanvas animationCanvas;
    Timer timer;
    JFrame frame;
    JPanel jp;
    private int numberAnim;

    private String ANIMALSCRIPT = "";

    private AbstractButton playButton;
    private AbstractButton pauseButton;
    private AbstractButton playReverseButton;
    private AbstractButton slideShowButton;
    private AbstractButton magicButton;
    AnimationController controller;



    public MyMini(int w, int h ){
        super(w,h);

    }

    @Override
    public void initConfig(int width, int height) {
        Animal animal = Animal.get();
        animal.setAutoloadLastFile(false);
        this.animalConfig = Animal.getAnimalConfiguration();
        new AnimalMainWindow(animal, this.animalConfig.getProperties(), false, true);
        this.animalConfig.initializeAllEditors();
        this.animation = new Animation();
        Animal.setAnimationLoadFinished(false);
        this.animationCanvas = new AnimationCanvas();
        this.animationCanvas.initSize();
        this.animationCanvas.setExplicitSize(new Dimension(width, height));
        this.animationCanvas.setVisible(true);
        // Set Wind ow of JFrame
        this.frame = new JFrame("Demo Frame");
        this.frame.getContentPane().setLayout(new BorderLayout());
        this.frame.getContentPane().add("Center", this.animationCanvas);
        this.frame.setSize(width, height + 50);
        /*animationWindow = animal.getMainWindow().getWindowCoordinator().getAnimationWindow(true);

        // generate the controller instance
        controller = new AnimationControlToolBarController(animationWindow, this);*/
        this.createMYButtons();

        this.frame.setVisible(true);
        this.frame.repaint();
        anim = animal;
    }

    public void setAnimationCode(String s){
        this.demoString = s;
    }



    public AnimalScriptParser getParser(boolean useANewOne) {
        AnimalScriptParser aSP = Animal.getAnimalScriptParser(useANewOne);
        return aSP;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public void setAnimation(Animation newAnim) {
        this.animation = newAnim;
        if(this.controller == null) {
            this.controller = new AnimationController(this.animation, this.animationCanvas, new AnimationState(this.animation));
        } else {
            this.controller.setAnimation(this.animation);
        }

    }
    public AnimationCanvas getAC(){return animationCanvas;}

    public JPanel getDisplayWindow(){return jp;}


    private void createMYButtons() {

        Translator translator = new Translator("AnimationPlayer", Locale.GERMANY);
        JPanel panel = new JPanel();
        panel.setSize(300,25);

        panel.setLayout(new GridLayout(1, 0));
        this.pauseButton = translator.getGenerator().generateJButton("pauseButton", (Object[])null, false, this);
        panel.add(this.pauseButton);
        this.playReverseButton = translator.getGenerator().generateJButton("playReverseButton", (Object[])null, false, this);
        panel.add(this.playReverseButton);
        this.playButton = translator.getGenerator().generateJButton("playButton", (Object[])null, false, this);
        panel.add(this.playButton);
        this.slideShowButton = translator.getGenerator().generateJButton("slideShowButton", (Object[])null, false, this);
        panel.add(this.slideShowButton);
        this.magicButton = translator.getGenerator().generateJButton("magicButton", (Object[])null, false, this);
        panel.add(this.magicButton);
        jp = panel;
        this.frame.getContentPane().add("South", panel);
    }

    public void dumpAnimation(Animation a) {
        if(a == null) {
            System.err.println("null");
        } else {
            AnimationListEntry[] entries = a.getAnimatorList();
            if(entries != null) {
                StringBuilder sbuffer = new StringBuilder(entries.length * 80);

                for(int i = 0; i < entries.length; ++i) {
                    AnimationListEntry ai = entries[i];
                    if(ai.mode == 1) {
                        sbuffer.append(ai.animator.toString());
                    }

                    if(ai.mode == 2) {
                        sbuffer.append(ai.link.toString());
                    }

                    sbuffer.append("\n");
                }

                System.err.println(sbuffer.toString());
            }
        }

    }

    @Override
    public void runDemo() {
        AnimalScriptParser parser = this.getParser(true);
        String animContents = demoString;
        if(BasicParser.stok == null) {
            parser.generateStreamTokenizer(animContents, false);
        }

        Animation anim = parser.importAnimationFrom(new StringReader(animContents), true);
        this.setAnimation(anim);
    }

    public String readAnimationFromFile(String filename) {
        StringBuilder fileContents = new StringBuilder(65536);

        try {
            InputStreamReader e = null;
            e = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(e);
            String currentLine = null;

            while((currentLine = br.readLine()) != null) {
                fileContents.append(currentLine).append("\n");
            }

            br.close();
        } catch (Exception var6) {
            System.err.println(var6.getMessage());
        }

        return fileContents.toString();
    }

    public void validateButtons() {
        AnimationState cState = this.controller.getAnimationState();
        this.playButton.setEnabled(cState.getNextStep() != 2147483647);
        this.slideShowButton.setEnabled(cState.getNextStep() != 2147483647);
        this.playReverseButton.setEnabled(cState.getPrevStep() != 0);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source == this.pauseButton) {
            if(this.controller != null) {
                this.controller.pause();
            }
        } else if(source == this.playButton) {
            if(this.controller != null) {
                AnimationState currentStep = this.controller.getAnimationState();
                if(currentStep.getNextStep() != 2147483647) {
                    this.controller.stepForward();
                }
            }

        } else if(source == this.playReverseButton) {
            if(this.controller != null) {
                this.controller.stepBackward();
            }
        } else if(source == this.slideShowButton) {
            if(this.controller != null) {
                this.controller.play();
            }
        } else if(source == this.magicButton) {
            int currentStep1 = this.controller.getAnimationState().getStep();
            String secondPart = "\ntriangle \"t\" (10, 10) (20, 20) (10, 20) filled fillColor cyan\nmove \"t\" \"s\" along line (10, 10) (20, 20) within 1000 ms";
            AnimalScriptParser parser = this.getParser(false);
            parser.generateStreamTokenizer(secondPart, false);
            Animation anim2 = null;

            try {
                anim2 = parser.programImport(false);
            } catch (IOException var8) {
                System.err.println("Could not load in animation for some reason...");
            }

            anim2.resetChange();
            this.setAnimation(anim2);
            this.controller.setStep(currentStep1, true);
        } else if(source == this.timer) {
            System.err.println("MUST NOT HAPPEN THAT WE CALL timer on XXDemo!");
        }

        this.validateButtons();
    }

    private void refreshAnimation() {
        int currentStep1 = this.controller.getAnimationState().getStep();
        String secondPart = "\ntriangle \"t\" (10, 10) (20, 20) (10, 20) filled fillColor cyan\nmove \"t\" \"s\" along line (10, 10) (20, 20) within 1000 ms";
        AnimalScriptParser parser = this.getParser(false);
        parser.generateStreamTokenizer(secondPart, false);
        Animation anim2 = null;

        try {
            anim2 = parser.programImport(false);
        } catch (IOException var8) {
            System.err.println("Could not load in animation for some reason...");
        }

        anim2.resetChange();
        this.setAnimation(anim2);
        this.controller.setStep(currentStep1, true);

    }

    public Animal getAnim(){
        return anim;
    }

    public void zoom(double level) {
        this.animationCanvas.setMagnification(level);
    }

    public double getZoom() {
        return this.animationCanvas.getMagnification();
    }

    public void setAnimalScript(String animalScript){this.ANIMALSCRIPT = animalScript;}

    public String getAnimalScript(){return ANIMALSCRIPT;}

    public void setCurAnimNumber(int in){numberAnim = in;}
    public int getCurAnimNumber(){return numberAnim;}

    public static void main(String[] args) {
        MiniAnimationPlayer x = new MiniAnimationPlayer(640, 480);
        x.runDemo();
    }
}



