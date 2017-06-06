package BachelorArbeitWs1617;

import animal.editor.AnnotationEditor;
import animal.gui.*;
import animal.main.Animal;
import animal.main.AnimalConfiguration;
import animal.main.AnimationWindow;
import animal.main.TimeLineWindow;
import animal.misc.HiddenObjectList;

/**
 * Created by deniz on 21.03.17.
 */
public class MyCoordinator extends WindowCoordinator {
    private TimeLineWindow timeLineWindow;
    private AnnotationEditor annotationEditor;
    private AnimationOverview animationOverview;
    public AnimationWindow animationWindow;
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


    public MyCoordinator(Animal animal, AnimalMainWindow animalMainWin) {
        super(animal, animalMainWin);
    }

@Override
    public AnimationWindow getAnimationWindow(boolean init) {
        if(this.animationWindow == null) {
            this.animationWindow = new AnimationWindow(this.animalInstance, AnimalConfiguration.getDefaultConfiguration().getProperties());
        }

        if(init && !this.animationWindow.isInitialized()) {
            this.animationWindow.init();
        }

        return this.animationWindow;
    }
}
