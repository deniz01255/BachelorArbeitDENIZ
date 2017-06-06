package BachelorArbeitWs1617;

/**
 * Created by deniz on 02.04.17.
 */

import animal.editor.AnnotationEditor;
import animal.gui.*;
import animal.main.Animal;
import animal.main.AnimalConfiguration;
import animal.main.AnimationWindow;
import animal.main.TimeLineWindow;
import animal.misc.HiddenObjectList;
import javax.swing.JOptionPane;

public class MyWindowCoordinator extends WindowCoordinator {
    private TimeLineWindow timeLineWindow;
    private AnnotationEditor annotationEditor;
    private AnimationOverview animationOverview;
    private AnimationWindow animationWindow;
    private DrawWindow drawWindow;
    private HiddenObjectList objectsWindow;
    private VariableView variableView;
    private boolean animationOverviewVisible = false;
    private boolean animationWindowVisible = false;
    private Animal animalInstance;
    private boolean annotationWindowVisible = false;
    private boolean drawWindowVisible = false;
    private boolean objectsWindowVisible = false;
    private boolean timeLineWindowVisible = false;
    private boolean variableViewVisible = false;

    public MyWindowCoordinator(Animal animal, AnimalMainWindow animalMainWin) {
        super(animal,animalMainWin);
        this.animalInstance = animal;
        if(this.animalInstance == null) {
            this.animalInstance = Animal.get();
        }

    }

    public AnimationOverview getAnimationOverview(boolean init) {
        if(this.animationOverview == null) {
            this.animationOverview = new AnimationOverview(this.animalInstance, AnimalConfiguration.getDefaultConfiguration().getProperties());
        }

        if(init && !this.animationOverview.isInitialized()) {
            this.animationOverview.init();
        }

        return this.animationOverview;
    }


    public AnimationWindow getAnimationWindow(boolean init) {
        if(this.animationWindow == null) {
            this.animationWindow = new AnimationWindow(this.animalInstance, AnimalConfiguration.getDefaultConfiguration().getProperties());
        }

        if(init && !this.animationWindow.isInitialized()) {
            this.animationWindow.init();
        }

        return this.animationWindow;
    }

    public AnnotationEditor getAnnotationEditor(boolean init) {
        if(this.annotationEditor == null) {
            this.annotationEditor = new AnnotationEditor(this.animalInstance, AnimalConfiguration.getDefaultConfiguration().getProperties(), AnimalConfiguration.getDefaultConfiguration().getProperties().getProperty("Animal.user", "guido"), AnimalConfiguration.getDefaultConfiguration());
        }

        return this.annotationEditor;
    }

    public DrawWindow getDrawWindow(boolean init) {
        if(this.drawWindow == null) {
            this.drawWindow = new DrawWindow(this.animalInstance, AnimalConfiguration.getDefaultConfiguration().getProperties());
        }

        if(init && !this.drawWindow.isInitialized()) {
            this.drawWindow.init();
        }

        return this.drawWindow;
    }

    public HiddenObjectList getObjectsWindow(boolean init) {
        if(this.objectsWindow == null) {
            this.objectsWindow = new HiddenObjectList(this.animalInstance, this.animalInstance.getAnimation());
        }

        this.objectsWindow.setStep(this.getDrawWindow(init).getStep());
        return this.objectsWindow;
    }

    public TimeLineWindow getTimeLineWindow(boolean init) {
        if(this.timeLineWindow == null) {
            this.timeLineWindow = new TimeLineWindow(this.animalInstance, this.animalInstance.getAnimation());
        }

        return this.timeLineWindow;
    }

    public VariableView getVariableView() {
        if(this.variableView == null) {
            this.variableView = new VariableView(this.animalInstance, this.animalInstance.getAnimation());
        }

        return this.variableView;
    }

    public boolean animationOverviewVisible() {
        return this.animationOverviewVisible;
    }

    public boolean animationWindowVisible() {
        return this.animationWindowVisible;
    }

    public boolean annotationWindowVisible() {
        return this.annotationWindowVisible;
    }

    public boolean drawWindowVisible() {
        return this.drawWindowVisible;
    }

    public boolean objectsWindowVisible() {
        return this.objectsWindowVisible;
    }

    public boolean timeLineWindowVisible() {
        return this.timeLineWindowVisible;
    }

    public boolean variablesWindowVisible() {
        return this.variableViewVisible;
    }

    public void showAnimationOverview() {
        this.getAnimationOverview(true).setVisible(true);
        this.animationOverview.setStep(1, false);
    }

    public void showAnimationWindow() {
        this.getAnimationWindow(true).setVisible(true);
    }

    public void showAnnotationWindow() {
        this.getAnnotationEditor(true).setVisible(true);
    }

    public void showDrawWindow() {
        if(!Animal.PREVENT_EDITING) {
            this.getDrawWindow(true).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(Animal.get(), "Editing is not possible within CrypTool, please start Animal\nwith java -jar Animal-x.y.z.jar from the command line,\nor by double clicking on the file");
        }

    }

    public void showObjectsWindow() {
        this.getObjectsWindow(true).setVisible(true);
    }

    public void showTimeLineWindow() {
        this.getTimeLineWindow(true).setVisible(true);
    }

    public void showVariableView() {
        this.getVariableView().setVisible(true);
    }
}

