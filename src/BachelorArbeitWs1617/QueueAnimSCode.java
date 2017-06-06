package BachelorArbeitWs1617;
import java.awt.*;
import java.util.List;

import algoanim.exceptions.LineNotExistsException;
import algoanim.primitives.*;
import algoanim.primitives.generators.AnimationType;
import algoanim.primitives.generators.Language;
import algoanim.properties.*;
import algoanim.util.Coordinates;
import algoanim.util.TicksTiming;
import algoanim.util.Timing;

/**
 * @author Dr. Guido R&ouml;&szlig;ling <roessling@acm.org>
 * @version 1.1 20140203
 */
public class QueueAnimSCode {

    /**
     * The concrete language object used for creating output
     */
    private static String[] buffer;
    private Language lang;
    private int iterator;
    private StringArray newDisplay;
    private int countSize;
    private String[] aOver;
    private Text sizeText;
    private Text markerSignF;
    private Text markerSignB;
    private ArrayMarker iMarker;
    private ArrayMarker backMarker;

    /**
     * Default constructor
     *
     * @param l
     *          the conrete language object used for creating output
     */
    public QueueAnimSCode(Language l) {
        // Store the language object
        lang = l;
        // This initializes the step mode. Each pair of subsequent steps has to
        // be divdided by a call of lang.nextStep();
        lang.setStepMode(true);
    }

    private static final String DESCRIPTION     = "In der Informatik bezeichnet eine Warteschlange " +
            "(englisch queue [kju]) eine häufig eingesetzte Datenstruktur. " +
            "Sie dient zur Zwischenspeicherung von Objekten in einer Reihenfolge," +
            "bevor diese weiterverarbeitet werden."+
            "Um eine Liste eines bestimmten Datentypen mit dessen instanzen zu befüllen"+
            "wird die Methode Enqueue genutzt, welche auf dem Listenobjekt ausgeführt wird.";

    private static final String SOURCE_CODE     = ".\n.\n."
            + "\n"
            + "\n  Queue newQueue = new Queue(); "
            + "\n\n  newQueue.Enqueue( \"first\" );"
            + "\n  newQueue.Enqueue( \"second\" );"
            + "\n  newQueue.Enqueue( \"third\" );"
            + "\n  newQueue.Enqueue( \"fourth\" );"
            + "\n  newQueue.Enqueue( \"fives\" );"
            + "\n\n."
            + "\n."
            + "\n.";

    /**
     * default duration for swap processes
     */
    public final static Timing  defaultDuration = new TicksTiming(30);

    /**
     * Sort the int array passed in
     *
     * @param a
     *          the array to be sorted
     */
    public void createAnimationQ(String[] a, List<String> Buffer) {

        TextProperties tProp3 = new TextProperties();
        tProp3.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);
        tProp3.set(AnimationPropertiesKeys.FONT_PROPERTY,
                new Font("Arial", Font.PLAIN, 24));

        //Text desc = lang.newText(new Coordinates(290, 127), DESCRIPTION, "Description", null,tProp3);


        lang.nextStep();

        iterator = 0;
        aOver = a;

        // Create Array: coordinates, data, name, display options,
        // default properties


        // Setting the Display Animation title ---------
        RectProperties rectProps = new RectProperties();

        TextProperties tProp = new TextProperties();
        tProp.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);

        lang.newText(new Coordinates(25,30), "Datenstruktur: Queue - Enqueue", "Animation-Name",null);

        lang.newRect(new Coordinates(20,20),new Coordinates(250,50), "NamensFeld", null);



        // start a new step after the array was created
        lang.nextStep();


        // Create SourceCode: coordinates, name, display options,
        // default properties

        // first, set the visual properties for the source code
        SourceCodeProperties scProps = new SourceCodeProperties();
        scProps.set(AnimationPropertiesKeys.CONTEXTCOLOR_PROPERTY, Color.BLUE);
        scProps.set(AnimationPropertiesKeys.FONT_PROPERTY,
                new Font("Arial", Font.PLAIN, 12));

        scProps.set(AnimationPropertiesKeys.HIGHLIGHTCOLOR_PROPERTY, Color.RED);
        scProps.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);

        // now, create the source code entity
        SourceCode sc = lang.newSourceCode(new Coordinates(20, 200), "sourceCode",
                null, scProps);

        setSourceCode(sc, Buffer);

        lang.nextStep();
        sc.highlight(0,0,false);
        lang.nextStep();


        ArrayProperties arrayProps = new ArrayProperties();
        arrayProps.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);
        arrayProps.set(AnimationPropertiesKeys.FILL_PROPERTY, Color.WHITE);
        arrayProps.set(AnimationPropertiesKeys.FILLED_PROPERTY, Boolean.TRUE);
        arrayProps.set(AnimationPropertiesKeys.ELEMENTCOLOR_PROPERTY, Color.BLACK);
        arrayProps.set(AnimationPropertiesKeys.ELEMHIGHLIGHT_PROPERTY, Color.RED);
        arrayProps.set(AnimationPropertiesKeys.CELLHIGHLIGHT_PROPERTY,
                Color.YELLOW);


        ArrayMarkerProperties arrayIMProps = new ArrayMarkerProperties();
        arrayIMProps.set(AnimationPropertiesKeys.LABEL_PROPERTY, "Front");
        arrayIMProps.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.MAGENTA);



        ArrayMarkerProperties arrayIMPropsBack = new ArrayMarkerProperties();
        arrayIMPropsBack.set(AnimationPropertiesKeys.LABEL_PROPERTY, "Back");
        arrayIMPropsBack.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.RED);

        iterator = 6;
        countSize = 0;

        newDisplay =lang.newStringArray(new Coordinates(20,140),a,"String Queue",null,arrayProps);
        sizeText = lang.newText(new Coordinates(300,30), "Size of Queue = "+ countSize, "Animation-Name",null);
        markerSignF = lang.newText(new Coordinates(300, 50),"Front Sign: MAGENTA - Arrow","ArrowFront",null);
        markerSignB = lang.newText(new Coordinates(300, 70),"Back Sign: RED - Arrow","ArrowBack" ,null);

        iMarker = lang.newArrayMarker(newDisplay, countSize, "Front", null, arrayIMProps);
        backMarker = lang.newArrayMarker(newDisplay, 0, "Back", null, arrayIMPropsBack);

        lang.nextStep();

        // unhiglight the creation code and highlight Enque part of code
        sc.unhighlight(0,0,false);



        lang.nextStep();


        //KORREKT BIS HIER !!!!! ####################



        // Highlight all cells
        //ia.highlightCell(0, ia.getLength() - 1, null, null);
        for (String s : Buffer) {


            try {
                // Start quicksort
                //  quickSort(ia, sc, 0, (ia.getLength() - 1));
                // Start Enqueue

                sc.highlight(iterator,0,false);
                lang.nextStep();
                enqueue(newDisplay, sc, s, arrayProps, aOver);

            } catch (LineNotExistsException e) {
                e.printStackTrace();
            }
        }

        //    sc.hide();
        // ia.hide();
        lang.nextStep();

    }

    private void enqueue(StringArray lbq, SourceCode sc, String enqueue, ArrayProperties aProp, String[] sA){
        countSize++;

        sizeText.hide();
        sizeText = lang.newText(new Coordinates(300,30), "Size of Queue = "+ countSize, "Animation-Name",null);




        ArrayMarkerProperties arrayIMProps = new ArrayMarkerProperties();
        arrayIMProps.set(AnimationPropertiesKeys.LABEL_PROPERTY, "Front");
        arrayIMProps.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.MAGENTA);


        // iMarker.hide();

        int counter;
        counter = 0;

        String[] newArray = new String[(countSize)];

        for(String s : sA){
            if(s != ""){
                newArray[counter+1] = s;
                counter++;
            }
        }

        newArray[0] = enqueue;


        /**for (int i = 0; i < newArray.length / 2; i++) {
         String temp = newArray[i];
         newArray[i] = newArray[newArray.length - 1 - i];
         newArray[newArray.length - 1 - i] = temp;
         }**/



        aOver = newArray;

        newDisplay.hide();
        newDisplay =lang.newStringArray(new Coordinates(20,140),newArray,"String Queue",null,aProp);
        iMarker.hide();
        iMarker = lang.newArrayMarker(newDisplay, counter, "Front", null, arrayIMProps);
        //backMarker = lang.newArrayMarker(newDisplay, 0, "Back", null, arrayIMPropsBack);
        // unhighlight the old line of code and increment by 1
        lang.nextStep();
        sc.unhighlight(iterator,0,false);
        iterator++;



        lang.nextStep();



    }



    /**
     * counter for the number of pointers
     *
     */
    private int pointerCounter = 0;



    protected String getAlgorithmDescription() {
        return DESCRIPTION;
    }

    protected String getAlgorithmCode() {
        return SOURCE_CODE;
    }

    public String getName() {
        return "Quicksort (pivot=last)";
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public String getCodeExample() {
        return SOURCE_CODE;
    }

    public static void main(String[] args) {
        // Create a new language object for generating animation code
        // this requires type, name, author, screen width, screen height
        Language l = Language.getLanguageInstance(AnimationType.ANIMALSCRIPT,
                "Queue - Enqueue", "Deniz Can Franz  Ertan", 640, 480);
        QueueAnimSCode s = new QueueAnimSCode(l);
        String[] a = {""};
        String[] buffer1 = {"first", "second", "third", "fourth", "fives", "seventh","eight"};
        buffer = buffer1;
        //s.createAnimationQ(a, buffer);
        System.out.println(l);
    }

    public void setSourceCode(SourceCode sc, List<String> buffer ) {
        sc.addCodeLine("Queue newQueue = new Queue();", null, 0, null); // 0
        sc.addCodeLine("", null, 0, null); // 1
        sc.addCodeLine(".", null, 0, null); // 2
        sc.addCodeLine(".", null, 0, null);// 3
        sc.addCodeLine(".", null, 0, null);// 4
        sc.addCodeLine("", null, 0, null); // 5

        for(String element : buffer){
            sc.addCodeLine("newQueue.Enqueue( "+element+" );", null, 1, null);
        }

        sc.addCodeLine("", null, 2, null); // 11
        sc.addCodeLine(".", null, 0, null); // 12
        sc.addCodeLine(".", null, 0, null); // 13
        sc.addCodeLine(".", null, 0, null); // 14
    }
}