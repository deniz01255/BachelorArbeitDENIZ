package BachelorArbeitWs1617;

import animal.gui.AnimalMainWindow;
import animal.gui.AnimationDisplayToolBar;
import animal.main.Animal;
import animal.main.AnimationWindow;
import animal.misc.MessageDisplay;
import translator.AnimalTranslator;
import translator.TranslatableGUIElement;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by deniz on 30.03.17.
 */
public class MyAnimationDisplayToolBar extends AnimationDisplayToolBar implements ActionListener, ChangeListener, ItemListener {
    private static final long serialVersionUID = 7455622329770177673L;
    public static final String NORMALIZE_DISPLAY = "normalizeDisplay";
    public static final String NORMALIZE_SPEED = "normalizeSpeed";
    private AnimationWindow animationWindow;
    private JSlider displaySpeedSlider;
    private JSlider displayMagnificationSlider;
    private Animal animal;
    public static final Double[] DEFAULT_ZOOM_FACTORS = new Double[]{new Double(0.1D), new Double(0.2D), new Double(0.25D), new Double(0.33D), new Double(0.5D), new Double(0.66D), new Double(0.71D), new Double(0.9D), new Double(1.0D), new Double(1.2D), new Double(1.25D), new Double(1.33D), new Double(1.41D), new Double(1.5D), new Double(2.0D), new Double(2.5D), new Double(3.0D), new Double(4.0D), new Double(5.0D), new Double(10.0D)};


    public MyAnimationDisplayToolBar(String title, AnimationWindow animWindow, Animal animal) {
        super(title, animWindow);
        this.animationWindow = animWindow;
        if(this.animationWindow == null) {
            this.animationWindow = animal.getMainWindow().getWindowCoordinator().getAnimationWindow(true);
        }
        this.animal = animal;
        this.buildToolBar();
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if(command.equals("normalizeSpeed")) {
            this.displaySpeedSlider.setValue(100);
            this.animationWindow.setSpeed(1.0D);
        } else if(command.equals("normalizeDisplay")) {
            this.displayMagnificationSlider.setValue(100);
        } else {
            MessageDisplay.errorMsg("nothingAssoc", command, 4);
        }

    }

    @Override
    public void stateChanged(ChangeEvent event) {
        Object source = event.getSource();
        double speed;
        if(source == this.displayMagnificationSlider && !this.displayMagnificationSlider.getValueIsAdjusting()) {
            speed = 0.01D * (double)this.displayMagnificationSlider.getValue();
            animal.getMainWindow().getWindowCoordinator().getAnimationWindow(true).getAnimationCanvas().setMagnification(speed);
            this.displayMagnificationSlider.setToolTipText(AnimalTranslator.translateMessage("magnificationAW.toolTipText", new Object[]{String.valueOf(this.displayMagnificationSlider.getValue())}));
        } else if(source == this.displaySpeedSlider && !this.displaySpeedSlider.getValueIsAdjusting()) {
            speed = 0.01D * (double)this.displaySpeedSlider.getValue();
            this.displaySpeedSlider.setToolTipText(AnimalTranslator.translateMessage("displaySpeed.toolTipText", new Object[]{String.valueOf(this.displaySpeedSlider.getValue())}));
            animal.getMainWindow().getWindowCoordinator().getAnimationWindow(true).setSpeed(speed);
        }

    }

    private AbstractButton createButton(String key, boolean isToggle, ActionListener listener, String command) {
        AbstractButton helper = AnimalTranslator.getGUIBuilder().generateJButton(key, (Object[])null, isToggle, listener);
        helper.setActionCommand(command);
        this.add(helper);
        return helper;
    }

    @Override
    public void buildToolBar() {
        this.getAccessibleContext().setAccessibleName(AnimalTranslator.translateMessage("fileToolbar"));
        this.setFloatable(false);
        TranslatableGUIElement generator = AnimalTranslator.getGUIBuilder();
        this.displaySpeedSlider = generator.generateJSlider("displaySpeed", new Object[]{"100"}, 0, 1000, 100, 200, 10, false, this);
        this.add(this.displaySpeedSlider);
        this.add(this.createButton("normalizeSpeed", false, this, "normalizeSpeed"));
        this.addSeparator(new Dimension(20, 0));
        Box zoomBox = generator.generateBorderedBox(2, "GUIResources.zoomBoxBL");
        this.displayMagnificationSlider = AnimalTranslator.getGUIBuilder().generateJSlider("magnificationAW", new Object[]{"100"}, 0, 500, 100, 100, 50, false, this);
        zoomBox.add(this.displayMagnificationSlider);
        zoomBox.add(this.createButton("normalizeDisplay", false, this, "normalizeDisplay"));
        this.add(zoomBox);
    }

    public void itemStateChanged(ItemEvent arg0) {
    }
}
