package BachelorArbeitWs1617;

import algoanim.exceptions.LineNotExistsException;
import algoanim.primitives.*;
import algoanim.primitives.generators.AnimationType;
import algoanim.primitives.generators.Language;
import algoanim.properties.*;
import algoanim.properties.meta.ArrowableAnimationProperties;
import algoanim.util.Coordinates;
import algoanim.util.TicksTiming;
import algoanim.util.Timing;
import java.lang.Math;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by deniz on 11.05.17.
 */
public class CircularAnimSCode {

    public int rad;
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
    public int countPos;
    public int[] xA;
    public int[] xB;
    public Double[] ax ;
    public Double[] ay ;
    private int readerPos;
    public String[] Entry;
    private boolean full;
    private SourceCode sc;
    private SourceCode scR;
    public boolean codeRAdd;
    public boolean codeWAdd;
    /**
     * Default constructor
     *
     * @param l the conrete language object used for creating output
     */
    public CircularAnimSCode(Language l) {
        countPos =0;
        // Store the language object
        lang = l;
        // This initializes the step mode. Each pair of subsequent steps has to
        // be divdided by a call of lang.nextStep();
        lang.setStepMode(true);
    }

    private static final String DESCRIPTION = "Warteschlangen sind häufig als Ringpuffer mit je einem Zeiger auf Anfang (In-Pointer) und Ende (Out-Pointer) implementiert. Die Besonderheit des Ringpuffers ist, dass er eine feste Größe besitzt. Dabei zeigt der In-Pointer auf das erste freie Element im Array, das den Ringpuffer repräsentiert, und der Out-Pointer auf das erste belegte Element in dem Array. Im Unterschied zum Array werden die ältesten Inhalte überschrieben, wenn der Puffer voll ist und weitere Elemente in den Ringpuffer abgelegt werden. Eine Implementierung des Ringpuffers sollte für den Fall, dass der Ringpuffer voll ist, entweder einen Pufferüberlauf signalisieren oder zusätzlichen Speicherplatz bereitstellen. In anderen Fällen kann das Überschreiben alter Elemente der Warteschlange und damit der Datenverlust gewollt sein.";


    private static final String SOURCE_CODEWrite = ".\n.\n."
            + "public boolean write(T value) { \n"
            + " if (isFull) {\n"
            + "return false;\n"
            + "}\n"
            + "ringBuffer.add(writePos, value);\n"
            + "writePos++;\n"
            + "writePos = writePos % size;\n"
            + "isEmpty = false;\n"
            + "if (writePos == readPos) {\n"
            + "isFull = true;\n"
            + "}\n"
            + "return true;\n"
            +"}\n";


    private static final String SOURCE_CODERead = ".\n.\n."
    +"public T read() {\n"
     +   "if (isEmpty) {\n"
      +      "return null;\n"
       + "}\n"
        +"T temp = ringBuffer.get(readPos);\n"
        +"readPos++;\n"
        +"readPos = readPos % size;\n"
        +"isFull = false;\n"
        +"if (writePos == readPos) {\n"
        +"isEmpty = true;\n"
        +"}\n"
        +"return temp;\n"
    +"}\n";


    /**
     * default duration for swap processes
     */
    public final static Timing defaultDuration = new TicksTiming(30);

    public void createAnimation(String[] a, List<String> Buffer) {
        codeRAdd = false;
        rad = 360;
        iterator = 0;
        aOver = a;

        // Create Array: coordinates, data, name, display options,
        // default properties


        // Setting the Display Animation title ---------
        RectProperties rectProps = new RectProperties();

        TextProperties tProp = new TextProperties();
        tProp.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);

        lang.newText(new Coordinates(25,30), "Abstrakter Datentyp - CircularBuffer: Read - Write", "Animation-Name",null);

        lang.newRect(new Coordinates(20,20),new Coordinates(350,50), "NamensFeld", null);

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
        sc = lang.newSourceCode(new Coordinates(20, 100), "sourceCode",
                null, scProps);
        scR = lang.newSourceCode(new Coordinates(620, 100), "sourceCode",
                null, scProps);

        setSourceCodeWrite(sc);
        setSourceCodeRead(scR);

        lang.nextStep();


        int[] array = new int[10];
        ax = new Double[7];
        ay = new Double[7];
        float rad = 100;
        Entry = new String[7];


        for (int n = 0; n < 7; n++) {
            Entry[n] = "";
            float x = (float) (3.0/7.0);
            ax[n] = (rad* Math.cos((float)n/7.0 * (float)2.0 * (float)Math.PI)+(float)450.0);
            ay[n]= (rad* Math.sin((float)n/7.0 * (float)2.0 * (float)Math.PI)+(float)325.0);
            lang.newCircle(new Coordinates(ax[n].intValue(), ay[n].intValue()), 20, "BufferOuter", null);

        }
        CircleProperties cPropFirst = new CircleProperties();
        cPropFirst.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.ORANGE);
        lang.newCircle(new Coordinates(ax[0].intValue(), ay[0].intValue()), 20, "BufferOuter", null,cPropFirst);
        full = false;
    }

    private void fillEntry() {


    }


    public void write(String input){
        sc.highlight(0);
        sc.highlight(1);
        if (!full) {
            sc.unhighlight(0);
            sc.unhighlight(1);
            sc.highlight(4);
            sc.highlight(5);
            sc.highlight(6);
            sc.highlight(7);
            TextProperties tProp = new TextProperties();
            tProp.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);
            CircleProperties cProp = new CircleProperties();
            cProp.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.RED);
            CircleProperties cPropBack = new CircleProperties();
            cPropBack.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);
            if (countPos > 0) {
                lang.newCircle(new Coordinates(ax[((countPos + 1) % 7)].intValue(), ay[((countPos + 1) % 7)].intValue()), 20, "BufferOuter", null, cProp);
            }
            lang.newCircle(new Coordinates(ax[countPos % 7].intValue(), ay[countPos % 7].intValue()), 20, "BufferOuter", null, cPropBack);
            lang.newText(new Coordinates(ax[countPos % 7].intValue(), ay[countPos % 7].intValue()), input, "Animation-Name", null);
            Entry[countPos % 7] = input;
            countPos++;
            sc.unhighlight(4);
            sc.unhighlight(5);
            sc.unhighlight(6);
            sc.unhighlight(7);
            sc.highlight(8);
            if ((readerPos % 7) == (countPos % 7)) {
                sc.unhighlight(8);
                sc.highlight(9);

                lang.newText(new Coordinates(100, 100), "FULL", "Animation-Name", null);
                full = true;
            }
            sc.unhighlight(8);
            sc.unhighlight(10);

            lang.nextStep();
            sc.unhighlight(10);

        }else{
            sc.unhighlight(0);
            sc.unhighlight(1);
            sc.highlight(2);
            lang.newText(new Coordinates(200,200), "Please Read First ... Buffer is FULL", "Animation-Name",null);

        }
        codeWAdd = true;

}

    public void read(){
        scR.highlight(0);
        lang.nextStep();
        scR.unhighlight(0);
        scR.highlight(1);
        lang.nextStep();
        if (Entry[readerPos]== ""){
            scR.unhighlight(1);
            scR.highlight(2);
            lang.nextStep();
        }else {
            TextProperties tProp = new TextProperties();
            tProp.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);
            CircleProperties cProp = new CircleProperties();
            cProp.set(AnimationPropertiesKeys.FILL_PROPERTY, Color.WHITE);
            cProp.set(AnimationPropertiesKeys.FILLED_PROPERTY, true);
            cProp.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.GREEN);
            CircleProperties cPropBack = new CircleProperties();
            cPropBack.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);
            if (readerPos > 0) {
                lang.newCircle(new Coordinates(ax[((readerPos - 1) % 7)].intValue(), ay[((readerPos - 1) % 7)].intValue()), 20, "BufferOuter", null, cPropBack);
            }
            scR.unhighlight(1);
            scR.unhighlight(2);
            scR.highlight(3);
            scR.highlight(4);
            scR.highlight(5);
            scR.highlight(6);
            lang.nextStep();
            Entry[readerPos%7]= "";
            lang.newCircle(new Coordinates(ax[readerPos % 7].intValue(), ay[readerPos % 7].intValue()), 20, "BufferOuter", null, cProp);
            //lang.newText(new Coordinates(ax[readerPos].intValue(),ay[readerPos].intValue()), "", "Animation-Name",null);

            readerPos++;
            full = false;
            scR.unhighlight(3);
            scR.unhighlight(4);
            scR.unhighlight(5);
            scR.unhighlight(6);
            scR.highlight(7);
            lang.nextStep();
            if( (readerPos%7)== (countPos%7)){
                scR.unhighlight(7);
                scR.highlight(8);
                lang.newText(new Coordinates(100,100), "EMPTY", "Animation-Name",null);
                lang.nextStep();

            }
            scR.unhighlight(7);
            scR.highlight(9);
            lang.nextStep();
            sc.unhighlight(9);

        }
        codeRAdd = true;

    }



    /**
     * counter for the number of pointers
     *
     */
    private int pointerCounter = 0;


    public Language getLang(){return lang;}

    protected String getAlgorithmDescription() {
        return DESCRIPTION;
    }

    protected String getAlgorithmCode() {
        return SOURCE_CODEWrite + SOURCE_CODERead;
    }

    public String getName() {
        return "Quicksort (pivot=last)";
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public String getCodeExample() {
        return SOURCE_CODEWrite + SOURCE_CODERead;
    }

    public static void main(String[] args) {
        // Create a new language object for generating animation code
        // this requires type, name, author, screen width, screen height
        Language l = Language.getLanguageInstance(AnimationType.ANIMALSCRIPT,
                "Read - Write | Circular Buffer", "Deniz Can Franz  Ertan", 640, 480);
        CircularAnimSCode s = new CircularAnimSCode(l);
        String[] a = {""};
        // List<String> buffer1 = {"first", "second", "third", "fourth", "fives", "seventh","eight"};
        //buffer = buffer1;

      //  s.createAnimation(a, buffer1);
        System.out.println(l);
    }

    public void setSourceCodeWrite(SourceCode sc) {
        sc.addCodeLine("public boolean write(T value) { ", null, 0, null); // 0
        sc.addCodeLine("if (isFull) {", null, 0, null); // 1
        sc.addCodeLine("return false;", null, 0, null); // 2
        sc.addCodeLine("}", null, 0, null);// 3
        sc.addCodeLine("ringBuffer.add(writePos, value);", null, 0, null);// 4
        sc.addCodeLine("writePos++;", null, 0, null); // 5
        sc.addCodeLine("writePos = writePos % size;", null, 2, null); // 11
        sc.addCodeLine("isEmpty = false;", null, 0, null); // 12
        sc.addCodeLine("if (writePos == readPos) {", null, 0, null); // 13
        sc.addCodeLine("isFull = true;", null, 0, null); // 14
        sc.addCodeLine("}", null, 0, null); // 15
        sc.addCodeLine("return true;", null, 0, null); // 16
        sc.addCodeLine("}", null, 0, null); // 17
    }

    public void setSourceCodeRead(SourceCode sc ) {
        sc.addCodeLine("public T read() {", null, 0, null); // 0
        sc.addCodeLine("if (isEmpty) {", null, 0, null); // 1
        sc.addCodeLine("return null;", null, 0, null); // 2
        sc.addCodeLine("}", null, 0, null);// 3
        sc.addCodeLine("T temp = ringBuffer.get(readPos);", null, 0, null);// 4
        sc.addCodeLine("readPos++;", null, 0, null); // 5
        sc.addCodeLine("readPos = readPos % size;", null, 2, null); // 11
        sc.addCodeLine("isFull = false;", null, 0, null); // 12
        sc.addCodeLine("if (writePos == readPos) {", null, 0, null); // 13
        sc.addCodeLine("}", null, 0, null); // 14
        sc.addCodeLine("return temp;", null, 0, null); // 15
        sc.addCodeLine("}", null, 0, null); // 16
    }


}





