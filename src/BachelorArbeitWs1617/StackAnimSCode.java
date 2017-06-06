package BachelorArbeitWs1617;

import java.awt.*;
import java.util.ArrayList;

import algoanim.primitives.SourceCode;
import algoanim.primitives.Text;
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
public class StackAnimSCode {

    /**
     * The concrete language object used for creating output
     */
    private Language lang;

    private StringBuilder assembly = new StringBuilder();
    private StringBuilder description = new StringBuilder();

    private  StringBuilder read = new StringBuilder();

    private int lefthand, righthand, intermResult;

    private boolean gotOP;

    private char operator;

    private boolean interimRESULTset = false;

    private int opCounter = 0;

    ArrayList<Integer> eAX = new ArrayList<Integer>();
    ArrayList<Integer> eBX = new ArrayList<Integer>();
    ArrayList<Integer> interimBX = new ArrayList<Integer>();











    /**
     * Default constructor
     *
     * @param l
     *          the conrete language object used for creating output
     */
    public StackAnimSCode(Language l) {
        // Store the language object
        lang = l;
        // This initializes the step mode. Each pair of subsequent steps has to
        // be divdided by a call of lang.nextStep();
        lang.setStepMode(true);
    }


    private static final String DESCRIPTION     = "QuickSort wählt ein Element aus der zu sortierenden Liste aus "
            + "\n(Pivotelement) und zerlegt die Liste in zwei Teillisten, eine untere, "
            + "\ndie alle Elemente kleiner und eine obere, die alle Elemente gleich oder "
            + "\ngrößer dem Pivotelement enthält.\nDazu wird zunächst ein Element von unten "
            + "\ngesucht, das größer als (oder gleichgroß wie) das Pivotelement und damit "
            + "\nfür die untere Liste zu groß ist. Entsprechend wird von oben ein kleineres "
            + "\nElement als das Pivotelement gesucht. Die beiden Elemente werden dann"
            + "\nvertauscht und landen damit in der jeweils richtigen Liste. Der Vorgang "
            + "\nwird fortgesetzt, bis sich die untere und obere Suche treffen. Damit sind "
            + "\ndie oben erwähnten Teillisten in einem einzigen Durchlauf entstanden. "
            + "\nSuche und Vertauschung können in-place durchgeführt werden."
            + "\nDie noch unsortierten Teillisten werden über denselben Algorithmus "
            + "\nin noch kleinere Teillisten zerlegt (z. B. mittels Rekursion) und, sobald "
            + "\nnur noch Listen mit je einem Element vorhanden sind, wieder zusammengesetzt. "
            + "\nDie Sortierung ist damit abgeschlossen.";

    private static final String SOURCE_CODE     = "public void quickSort(int[] array, int l, int r)"              // 0
            + "\n{"                                                                                                   // 1
            + "\n  int i, j, pivot;"                                                                                  // 2
            + "\n  if (r>l)"                                                                                          // 3
            + "\n  {"                                                                                                 // 4
            + "\n    pivot = array[r];"                                                                               // 5
            + "\n    for (i = l; j = r - 1; i < j; )"                                                                 // 6
            + "\n    {"                                                                                               // 7
            + "\n      while (array[i] <= pivot && j > i)"                                                            // 8
            + "\n        i++;"                                                                                        // 9
            + "\n      while (pivot < array[j] && j > i)"                                                             // 10
            + "\n        j--;"                                                                                        // 11
            + "\n      if (i < j)"                                                                                    // 12
            + "\n        swap(array, i, j);"                                                                          // 13
            + "\n    }"                                                                                               // 14
            + "\n    if (pivot < array[i])"                                                                           // 15
            + "\n      swap(array, i, r);"                                                                            // 16
            + "\n    else"                                                                                            // 17
            + "\n      i=r;"                                                                                          // 18
            + "\n    quickSort(array, l, i - 1);"                                                                     // 19
            + "\n    quickSort(array, i + 1, r);"                                                                     // 20
            + "\n  }"                                                                                                 // 21
            + "\n}";                                                                                                  // 22

    /**
     * default duration for swap processes
     */
    public final static Timing  defaultDuration = new TicksTiming(30);

    /**
     * Parse creates an Assembler code out of the arithmetic expression
     * @param expr
     */
    public void parse(String expr)throws Exception {

        //converts String into Char array, to iterrate over the whole chars
        char exprCHAR[] = expr.toCharArray();
        int sizeofEXPR = exprCHAR.length;
        int lParenCHECK = 0, rParenCHECK = 0, charInside = 0;

        System.out.print("Method: 'parse' opened \n");

        // check first if all the paren where set right !!
        // if number of LParen != RParen ... throw exception of "Paren where set wrong"
        for (int i = 0; i < sizeofEXPR; i++) {
            if (exprCHAR[i] == '(') {
                lParenCHECK++;
            } else if (exprCHAR[i] == ')') {
                rParenCHECK++;

            } else if (exprCHAR[i] == '0' || exprCHAR[i] == '1' || exprCHAR[i] == '2' || exprCHAR[i] == '3' || exprCHAR[i] == '4'
                    || exprCHAR[i] == '5' || exprCHAR[i] == '6' || exprCHAR[i] == '7' || exprCHAR[i] == '9' || exprCHAR[i] == '+'
                    || exprCHAR[i] == '-' || exprCHAR[i] == '*' || exprCHAR[i] == '/') {


            }else{
                charInside++;
            }
        }


        if (lParenCHECK == rParenCHECK) {
        } else {
            throw new IllegalArgumentException("You have set a wrong number of Paren. Please check if you missed parens or made to much!");
        }

        if (charInside == 0) {
        } else {
            throw new IllegalArgumentException("Your Arithmetic expression contains invalid Chars. Only: [0,1,2,3,4,5,6,7,8,9,+,-,*,/] are comparable.");
        }

        //##########   checking for correct amopunt of Paren and no uncomman char is absolved.  #########
        charToAssembly(exprCHAR);
    }

    public int charToAssembly(char[] exprCHAR) {


        for (int i = 0; i < exprCHAR.length; i++) {
            if (exprCHAR[i] == '+' || exprCHAR[i] == '*' || exprCHAR[i] == '-' || exprCHAR[i] == '/') {
                gotOP = true;
                operator = exprCHAR[i];
                for (int j = i-1; j >= 0 ; j--) {
                    if((exprCHAR[i-1] )== ')'){
                        lefthand = intermResult;
                        break;

                    }
                    else if(exprCHAR[j] != '('){
                        read.append(exprCHAR[j]);
                    }
                    else{
                        read.reverse();
                        lefthand = Integer.valueOf(read.toString());
                        assembly.append("movl $"+lefthand+", %eax \n");
                        read.delete(0,read.length());
                        break;
                    }

                }


            }else if(exprCHAR[i] == ')'){
                for (int k = i-1; k >= 0 ; k--) {
                    if(exprCHAR[k] == '+'|| exprCHAR[k] == '*' || exprCHAR[k] == '-' || exprCHAR[k] == '/'){
                        read.reverse();
                        righthand = Integer.valueOf(read.toString());
                        assembly.append("movl $"+righthand+", %ebx \n");
                        calc(operator);
                        break;
                    }else{
                        read.append(exprCHAR[k]);
                    }

                }
            }


        }
        assembly.append("pushl %eax\npushl $intout\n\ncall printf \nmovl $1, %eax\nint $0x80\n\n");
        System.out.print(assembly);
        System.out.println("content of %eax = "+ intermResult);
        assemblyToStack(assembly);
        return intermResult;
    }

    private void calc(char op) {
        switch (op) {
            case '+':

                intermResult = lefthand + righthand;
                eAX.add(lefthand);
                eBX.add(righthand);
                interimBX.add(intermResult);
                if(opCounter == 0 ){
                    opCounter++;
                    assembly.append("addl $ebx, %eax\n");
                    assembly.append("pushl %eax\n\n");

                }else{
                    assembly.append("pop %eax\n");
                    assembly.append("addl $ebx, %eax\n");
                    assembly.append("pushl %eax\n\n");
                }


                break;
            case '-':
                intermResult = lefthand - righthand;
                eAX.add(lefthand);
                eBX.add(righthand);
                interimBX.add(intermResult);
                if(opCounter == 0) {
                    opCounter++;
                    assembly.append("subl $ebx, %eax\n");
                    assembly.append("pushl %eax\n\n");
                }else{
                    assembly.append("pop %eax\n");
                    assembly.append("addl $ebx, %eax\n");
                    assembly.append("pushl %eax\n\n");

                }
                break;

            case '/':
                intermResult = lefthand / righthand;
                eAX.add(lefthand);
                eBX.add(righthand);
                interimBX.add(intermResult);
                if(opCounter == 0) {
                    opCounter++;
                    assembly.append("idiv $ebx\n");
                    assembly.append("pushl %eax\n\n");
                }else{
                    assembly.append("pop %eax\n");
                    assembly.append("idiv $ebx\n");
                    assembly.append("pushl %eax\n\n");
                }
                break;

            case '*':
                intermResult = lefthand * righthand;
                eAX.add(lefthand);
                eBX.add(righthand);
                interimBX.add(intermResult);
                if(opCounter == 0) {
                    opCounter++;
                    assembly.append("imull %eax, %eax, %ebx\n");
                    assembly.append("pushl %eax\n\n");
                }else{
                    assembly.append("pop %eax\n");
                    assembly.append("imull %eax, %ebx, %eax\n");
                    assembly.append("pushl %eax\n\n");
                }

                break;

        }
        read.delete(0, read.length());
    }


    public void assemblyToStack(StringBuilder code) {

        SourceCodeProperties scProps = new SourceCodeProperties();
        scProps.set(AnimationPropertiesKeys.CONTEXTCOLOR_PROPERTY, Color.BLUE);
        scProps.set(AnimationPropertiesKeys.FONT_PROPERTY,
                new Font("Arial", Font.PLAIN, 12));

        scProps.set(AnimationPropertiesKeys.HIGHLIGHTCOLOR_PROPERTY, Color.RED);
        scProps.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);

        TextProperties tProp3 = new TextProperties();
        tProp3.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);
        tProp3.set(AnimationPropertiesKeys.FONT_PROPERTY,
                new Font("Arial", Font.PLAIN, 24));


       // Text anzeige =lang.newText(new Coordinates(50, 110), "Weitere informationen finden sie im Haupt-Menü" , "Stack-0", null,tProp3);
       // Text anzeige1 =lang.newText(new Coordinates(50, 125), "In der entsprechenden Kategorie !" , "Stack-0", null,tProp3);


       /** SourceCode TEXT = lang.newSourceCode(new Coordinates(20, 100), "sourceCode",
                null, scProps);

        TEXT.addCodeLine("QuickSort wählt ein Element aus der zu sortierenden Liste aus \n",null,0,null);
        TEXT.addCodeLine("(Pivotelement) und zerlegt die Liste in zwei Teillisten, eine untere,\n ",null,0,null);
        TEXT.addCodeLine("die alle Elemente kleiner und eine obere, die alle Elemente gleich oder  \n",null,0,null);
        TEXT.addCodeLine("größer dem Pivotelement enthält.\nDazu wird zunächst ein Element von unten \n",null,0,null);
        TEXT.addCodeLine("gesucht, das größer als (oder gleichgroß wie) das Pivotelement und damit \n",null,0,null);
        TEXT.addCodeLine("QuickSort wählt ein Element aus der zu sortierenden Liste aus \n",null,0,null);


        lang.nextStep();**/



        RectProperties rectProps = new RectProperties();

        TextProperties tProp = new TextProperties();
        tProp.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLACK);
        tProp.set(AnimationPropertiesKeys.FONT_PROPERTY,
                new Font("Arial", Font.PLAIN, 14));

        TextProperties tProp2 = new TextProperties();
        tProp2.set(AnimationPropertiesKeys.COLOR_PROPERTY, Color.BLUE);
        tProp2.set(AnimationPropertiesKeys.FONT_PROPERTY,
                new Font("Arial", Font.PLAIN, 18));



        Text top =lang.newText(new Coordinates(290, 127), "TOP OF STACK", "Stack-0", null,tProp2);
        Text register = lang.newText(new Coordinates(170, 67), "REGISTER -> ", "Animation-Name", null,tProp2);
        Text eax = lang.newText(new Coordinates(300, 60), "%EAX: ", "Animation-Name", null,tProp);
        Text ebx =lang.newText(new Coordinates(300, 75), "%EBX: ", "Animation-Name", null,tProp);
        Text eaxEntry = lang.newText(new Coordinates(310, 60), "", "Animation-Name", null,tProp);
        Text ebxEntry =lang.newText(new Coordinates(310, 75), "", "Animation-Name", null,tProp);



        lang.newText(new Coordinates(25, 30), "Stack - Arithmetic Expression", "Animation-Name", null);
        lang.newRect(new Coordinates(20, 20), new Coordinates(200, 50), "NamensFeld", null);

        Text topStack =lang.newText(new Coordinates(290, 127), "", "Stack-0", null,tProp2);
        lang.newRect(new Coordinates(300, 150), new Coordinates(400, 200), "NamensFeld", null);

        lang.newText(new Coordinates(305, 266), "", "Stack-1", null);
        lang.newRect(new Coordinates(300, 200), new Coordinates(400, 250), "NamensFeld", null);

        lang.newText(new Coordinates(305, 276), "", "Stack-2", null);
        lang.newRect(new Coordinates(300, 250), new Coordinates(400, 300), "NamensFeld", null);

        lang.newText(new Coordinates(305, 286), "", "Stack-3", null);
        lang.newRect(new Coordinates(300, 300), new Coordinates(400, 350), "NamensFeld", null);


       // lang.nextStep();



        SourceCode sc = lang.newSourceCode(new Coordinates(20, 100), "sourceCode",
                null, scProps);


        sc.addCodeLine("", null, 0, null); // 1

        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '\n') {
                StringBuilder line = new StringBuilder();
                for (int j = i-1 ; j >=0 ; j--) {
                    if(code.charAt(j) =='\n' ){break;}
                    line.append(code.charAt(j));
                }
                line.reverse();
                sc.addCodeLine(line.toString(),null,0,null);
                line.delete(0,line.length());
            }
        }
        lang.nextStep();

        int counter =0;
        int aCounter =0;
        int bCounter =0;

        int aDoubleCounter =0;
        int bDoubleCounter =0;


        for (int i = 0; i <sc.length() ; i++) {
            if((i==4 || i == 9 || i == 14 || i == 19|| i == 24) && i< sc.length()-8){
                topStack.hide();

                topStack = lang.newText(new Coordinates(345, 160), interimBX.get(counter).toString(), "Stack-0", null,tProp3);
                counter++;
            }
            if (i != 0) {
                sc.unhighlight(i-1,0,false);
                sc.highlight(i,0,false);
                lang.nextStep();



            }if((i==0 || i == 3 || i == 7 || i == 8|| i == 12 || i == 13 ) && i< sc.length()-8){
                if(i==3|| i==7){

                    eaxEntry.hide();
                    eaxEntry = lang.newText(new Coordinates(370, 60), interimBX.get(0).toString(), "Animation-Name", null,tProp);
                }else if(i==8|| i==12){
                    eaxEntry.hide();
                    eaxEntry = lang.newText(new Coordinates(370, 60), interimBX.get(1).toString(), "Animation-Name", null,tProp);
                }else if((i==13)){
                    eaxEntry.hide();
                    eaxEntry = lang.newText(new Coordinates(370, 60), interimBX.get(2).toString(), "Animation-Name", null,tProp);
                }else if(i==0) {
                    eaxEntry.hide();
                    eaxEntry = lang.newText(new Coordinates(370, 60), eAX.get(aDoubleCounter).toString(), "Animation-Name", null,tProp);
                    aCounter++;
                }

            }if((i==1 || (i%5)==0 && i!=0) && i< sc.length()-8){

                ebxEntry.hide();
                ebxEntry = lang.newText(new Coordinates(370, 75), eBX.get(bCounter).toString(), "Animation-Name", null,tProp);
                bCounter++;

            }


            else {

                sc.highlight(i, 0, false);
                lang.nextStep();
            }
        }


    }







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
                "Stack Arithmetic", "Guido Rößling", 640, 480);

        StackAnimSCode s = new StackAnimSCode(l);
        String expression = "((((3-1)/2)+1)*72)";
        try {
            s.parse(expression);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(l);
    }



}
