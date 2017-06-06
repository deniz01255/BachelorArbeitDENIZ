package BachelorArbeitWs1617;


import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import animal.gui.AnimalMainWindow;
import animal.gui.AnimationControlToolBar;
import animal.gui.AnimationControlToolBarController;
import translator.AnimalTranslator;
import translator.TranslatableGUIElement;
import animal.main.Animal;
import animal.main.Animation;
import animal.main.AnimationState;
import animal.main.AnimationWindow;
import animal.main.ButtonController;
import animal.main.Link;
import animal.misc.MessageDisplay;

/**
 * @author guido
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MyAnimationControlToolBar extends AnimationControlToolBar {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3065614895282195843L;

    /**
     * the constant action command for the "end" element
     */
    public final static String END = "end";

    /**
     * the constant action command for the "next" element
     */
    public final static String NEXT_BUTTON = "next";

    /**
     * the constant action command for the "pause" element
     */
    public final static String PAUSE = "pause";

    /**
     * the constant action command for the "play" element
     */
    public final static String PLAY = "play";

    /**
     * the constant action command for the "prev" element
     */
    public final static String PREV = "prev";

    /**
     * the constant action command for the "reverse play" element
     */
    public final static String REVERSE_PLAY = "reverse play";

    /**
     * the constant action command for the "reverse slide show" element
     */
    public final static String REVERSE_SLIDE_SHOW = "reverse slideshow";

    /**
     * the constant action command for the "slide show" element
     */
    public final static String SLIDE_SHOW = "slideshow";

    /**
     * the constant action command for the "save as" element
     */
    public final static String START = "start";

    /**
     * the constant action command for the "step slider" element
     */
    public final static String STEP_NR_FIELD = "stepNumberField";

    /**
     * the constant action command for the "step slider" element
     */
    public final static String STEP_SLIDER = "stepSlider";

    /**
     * The animationWindow instance, needed for enabling / disabling controls
     */
    private AnimationWindow animationWindow;

    /**
     * the current button controller
     */
    private ButtonController buttonController;

    /**
     * The standard controller for all elements
     */
    private MyAnimationControlToolBarController controller;

    /**
     * the button for "jump to the animation's end"
     */
    private AbstractButton endButton;

    /**
     * the button for "go to next animation step"
     */
//  private AbstractButton nextButton;

    /**
     * the button for "pause the animation"
     */
    private AbstractButton pauseButton;

    /**
     * the button for "play the animation"
     */
    private AbstractButton playButton;

    /**
     * the button for "previous animation step"
     */
    private AbstractButton prevButton;

    /**
     * the button for "reverse play of the animation"
     */
//  private AbstractButton reversePlayButton;

    /**
     * the button for "reverse slide show of the animation"
     */
    private AbstractButton reverseSlideButton;

    /**
     * the button for "slide show of the animation"
     */
    private AbstractButton slideShowButton;

    /**
     * the button for "start of the animation"
     */
    private AbstractButton startButton;

    /**
     * the animation step slide control
     */
    private JSlider stepSlider;

    /**
     * the step field control
     */
    private JTextField stepField;

    private JLabel nrSteps;

    /**
     * creates the main tool bar for Animal
     *
     * @param title not used at the moment
     * @param anAnimWindow the concrete AnimationWindow instance
     * for which the toolbar is instantiated.
     */
    public MyAnimationControlToolBar(String title, AnimationWindow anAnimWindow) {
        super(title,anAnimWindow);

        // store the reference to the AnimationWindow instance
        // if null, create it first!
        if (anAnimWindow != null)
            animationWindow = anAnimWindow;
        else
            animationWindow = AnimalMainWindow.getWindowCoordinator().getAnimationWindow(true);

        // generate the controller instance
        controller = new MyAnimationControlToolBarController(animationWindow, this);

        // build the actual tool bar
        buildToolBar();
    }

    /**
     * activates or deactivates the change listener for the
     * animation step slider
     *
     * @param isActive if true, enable the change listener for the
     * step slider, else deactivate it
     */
    public void activateStepSliderListener(boolean isActive) {
        if (stepSlider == null) {
            return;
        }

        if (isActive) {
            stepSlider.addChangeListener(controller);
        }
        else {
            stepSlider.removeChangeListener(controller);
        }
    }

    /**
     * determines if buttons may be accessed, based on the step number
     *
     * @param currentStep the step number of the current animation step
     * @return true if buttons can be accessed, else false
     */
    public boolean allowButtonAccess(int currentStep) {
        return true;
    }

    /**
     * build the animation tool bar
     */
    public void buildToolBar() {
        TranslatableGUIElement generator = AnimalTranslator.getGUIBuilder();
        // configure the JToolBar
        getAccessibleContext().setAccessibleName(AnimalTranslator.translateMessage(
                "animationControlToolBar"));
        setFloatable(true);

        // requested operations:
        // 1. jump to start
        // 2. one step back
        // 3. play
        // 4. pause
        // 5. jump to end

        // kiosk mode backwards, forwards
        Box basicControlBox = generator.generateBorderedBox(BoxLayout.LINE_AXIS,
                "basicControlBL");

        // first row:  jump to start
        startButton = createButton(basicControlBox, "startButton", false,
                controller, START);

        // first row:  go to previous step
        prevButton = createButton(basicControlBox, "prevButton", false,
                controller, PREV);


//    // first row:  play the current step in reverse mode
//    reversePlayButton = createButton("playReverseButton", false, controller,
//        REVERSE_PLAY);

        // first row:  pause the animation
        pauseButton = createButton(basicControlBox, "pauseButton", false,
                controller, PAUSE);

        // first row:  play the animation in forward mode
        playButton = createButton(basicControlBox, "playButton", false,
                controller, PLAY);


//    // first row:  save as
//    nextButton = createButton("nextButton", false, controller, NEXT_BUTTON);

        // third row:  jump to the end of the animation
        endButton = createButton(basicControlBox, "endButton", false,
                controller, END);

        add(basicControlBox);
        addSeparator();

        Box kioskBox = generator.generateBorderedBox(BoxLayout.LINE_AXIS,
                "kioskModeBL");

        // first row:  new
        reverseSlideButton = createButton(kioskBox, "revSlideShowButton",
                false, controller, REVERSE_SLIDE_SHOW);

        // first row:  play in slide show mode
        slideShowButton = createButton(kioskBox, "slideShowButton", false,
                controller, SLIDE_SHOW);
        add(kioskBox);

        addSeparator();

        // second row: the label for the step control
        Box stepBox = generator.generateBorderedBox(BoxLayout.LINE_AXIS,
                "stepBL");

        // generate the "step number" text field
        stepField = generator.generateJTextField("stepChoiceAW",
                null, 3, "1");
        stepField.setMaximumSize(new Dimension(40, 30));
        stepField.setActionCommand(STEP_NR_FIELD);
        stepField.addActionListener(controller);

        // ... and add it to the list!
        stepBox.add(stepField);

        nrSteps = new JLabel("/ 0");
        stepBox.add(nrSteps);

        // generate the animation step slide
        stepSlider = AnimalTranslator.getGUIBuilder().generateJSlider("sliderValue",
                new Object[] { "100%" }, 0, 100, 0, false, controller);

        stepBox.add(stepSlider);
        add(stepBox);
    }

    /**
     * Creates an abstract button to be plugged into the toolbar
     *
     * @param key the I18N key for the button
     * @param isToggle if true, use a toggleable button
     * @param listener the ActionListener for this element
     * @param command the actual "actionCommand" to be used - useful
     * to avoid costly I18N conversions to a "flat" format
     * @return
     */
    private AbstractButton createButton(Box container, String key, boolean isToggle,
                                        ActionListener listener, String command) {
        // first row:  new
        AbstractButton helper =
                AnimalTranslator.getGUIBuilder().generateJButton(key, null,
                        isToggle, listener);
        helper.setActionCommand(command);
        container.add(helper);

        return helper;
    }

    /**
     * determine which buttons should be "on" and which should be "off" now.
     * For example, the first step will deactivate all "back" buttons.
     *
     * @param step the current animation step.
     */
    public void determineButtonState(int step) {
        AnimationState animState = animationWindow.getAnimationState();
        Link thisLink = animState.getAnimation().getLink(animState.getStep());
        if (thisLink.getMode() != Link.WAIT_CLICK) {
            enableControls(getButtonController().allowButtonAccess(step));
        }
        else {
            MessageDisplay.addDebugMessage("turnOffControls");
            enableControls(false);
        }

        stepField.setText(String.valueOf(step));
        stepField.repaint();
    }

    /**
     * activate or deactivate (part of) the animation controls
     *
     * @param enabled true if the controls shall be "on", else false
     */
    public void enableControls(boolean enabled) {
        pauseButton.setEnabled(enabled);
        stepField.setEnabled(enabled);
        stepSlider.setEnabled(enabled);

        // determine if 'reverse' buttons may be active:
        // must be 'enabled' and not at start of animation
        int animationWindowStep = animationWindow.getStep();
        int lastStep = animationWindow.getAnimationState().getLastStep();
        int firstStep = animationWindow.getAnimationState().getFirstRealStep();
        boolean active = enabled && (animationWindow.getStep() != firstStep);

        // do not allow user access to 'reverse' buttons if at start
        startButton.setEnabled(active);
        prevButton.setEnabled(active);
//    reversePlayButton.setEnabled(active);
        reverseSlideButton.setEnabled(active);

        // determine if 'forward' buttons may be active:
        // must be 'enabled' and not at end of animation
        active = enabled && (animationWindowStep != lastStep);

        // do not allow user access to 'forward' buttons if at start
        playButton.setEnabled(active);
        slideShowButton.setEnabled(active);
//    nextButton.setEnabled(active);
        endButton.setEnabled(active);
    }


    /**
     * activate or deactivate the play button
     *
     * @param enabled true if the controls shall be "on", else false
     */
    public void enablePlayButton(boolean enabled) {
        playButton.setEnabled(enabled);
    }


    /**
     * returns the current button controller
     *
     * @return the button controller instance
     */
    public ButtonController getButtonController() {
        if (buttonController == null) {
            setButtonController(Animal.get());
        }

        return buttonController;
    }

    /**
     * assigns the current button controller
     *
     * @param aController the new ButtonController instance
     */
    public void setButtonController(ButtonController aController) {
        buttonController = aController;
    }

    /**
     * update the graphical components due to the changed animation step
     *
     * @param targetStep the target step
     * @param animState the current AnimationState object
     */
    public void setStep(int targetStep, AnimationState animState) {
        int animationPercentage = 0;
        Animation anim = animState.getAnimation();

        if (anim.getNrOfSteps() != 0) {
            // turn off the slide listener to prevent unwanted events
            activateStepSliderListener(false);

            // determine the percentage of the full animation we have reached
            animationPercentage = Math.round(((100 * anim.getPositionOfStep(
                    targetStep)) + 1) / (anim.getNrOfSteps() - 1));

            // update the slider...
            stepSlider.setValue(animationPercentage);

            // ... and re-activate the listener!
            activateStepSliderListener(true);
        }

        // determine the "appropriat" link label
        String linkLabel = anim.getLink(targetStep).getLinkLabel();

        // assign either the link label or the default, showing the animation %
        if (linkLabel == null) {
            linkLabel = AnimalTranslator.translateMessage("sliderValue.toolTipText",
                    new Object[] { animationPercentage + "%" });
        }
        stepSlider.setToolTipText(linkLabel);

        // also update the step field display!
        stepField.setText(String.valueOf(targetStep));

        // make sure the change is visible...
        stepField.repaint();
    }

    public void setNrOfSteps(int nrTotalSteps) {
        if (nrTotalSteps > 0)
            nrSteps.setText("/ " + nrTotalSteps);
    }
}
