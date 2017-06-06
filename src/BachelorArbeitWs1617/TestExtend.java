package BachelorArbeitWs1617;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import animal.animator.Animator;
import animal.graphics.PTGraphicObject;
import animal.gui.AnimalMainWindow;
import animal.gui.AnimationControlToolBar;
import animal.gui.AnimationDisplayToolBar;
import animal.gui.GraphicVector;
import animal.main.*;
import animal.misc.XProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import animal.animator.Animator;
import animal.graphics.PTGraphicObject;
import animal.gui.AnimalMainWindow;
import animal.gui.AnimationControlToolBar;
import animal.gui.AnimationDisplayToolBar;
import animal.gui.GraphicVector;
import animal.main.Animal;
import animal.main.AnimalFrame;
import animal.main.Animation;
import animal.main.AnimationCanvas;
import animal.main.AnimationState;
import animal.main.ButtonController;
import animal.main.Link;
import animal.misc.XProperties;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TestExtend extends AnimationWindow {
    private static final long serialVersionUID = -5650098641898883312L;
    public static final double MAX_SPEED_FACTOR = 10.0D;
    public static final int PADDING_SCROLLBAR_RIGHT = 20;
    public static final int PADDING_TOOLBARS_TOP_BOTTOM = 80;
    private AnimationState ani = null;
    public MyCanvas animationCanvas;
    private ScrollPane sp;
    private boolean pause = false;
    private boolean slideShowMode = false;
    private boolean forwardMode = true;
    private double ticks;
    private Timer timer;
    private double speed = 1.0D;
    private boolean isSlowMode = false;
    private Vector<Image> animationImages = new Vector(50, 15);
    private Container workContainer = null;
    private AnimationControlToolBar animationControlToolBar;
    private AnimationDisplayToolBar displayControlToolBar;


    public TestExtend(Animal animalInstance, XProperties properties) {
        super(animalInstance, properties);
        this.setDependentContainer(this.getContentPane());
    }

@Override
    public void init() {
        this.workContainer().setLayout(new BorderLayout(0, 0));
        this.animationControlToolBar = new AnimationControlToolBar("AnimationControls", this);
        this.workContainer().add("South", this.animationControlToolBar);
        this.displayControlToolBar = new AnimationDisplayToolBar("Animation Display Controls", this);
        this.workContainer().add("North", this.displayControlToolBar);
        if(this.animationCanvas == null) {
            this.animationCanvas = new MyCanvas();
        }


        if(this.sp == null) {
            this.sp = new ScrollPane();
            this.sp.add(this.animationCanvas);
        }

        this.workContainer().add("Center", this.sp);
        this.setTitle("Animal Animation");

    }

}
