package BachelorArbeitWs1617;

import animal.exchange.AnimationExporter;
import animal.exchange.AnimationImporter;
import animal.exchange.AnimationPrintJob;
import animal.gui.AnimalCollectionTypes;
import animal.gui.AnimationDisplayToolBar;
import animal.gui.LoadFromCollection;
import animal.main.Animal;
import animal.main.Animation;
import animal.misc.MessageDisplay;
import animal.vhdl.exchange.VHDLAnimationImporter;
import htdptl.gui.HtDPTLWizard;
import translator.AnimalTranslator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

import static BachelorArbeitWs1617.Gui.mAP;

/**
 * Created by deniz on 01.12.16.
 */
public class MyToolBar extends JToolBar {
    public JButton saveButton;
    public JButton openButton;
    public JButton mainMenuButton;
    private Gui gui;
    private boolean firstElement;

    public ScrollPane sp1;
    public MyPane mpane;
    public Animation currentAnimation;
    public JFrame fenster;

    private JPanel upperHalf;
    private JScrollPane mainWindow;

    private GUIGO guigo;


    private AnimationDisplayToolBar displayControlToolBar;
    JToolBar toolbar;
    MyMini mM;
    AnimationImporter aI;




    public MyToolBar(JFrame fenster, MyMini mM){
        this.fenster = fenster;
        this.mM = mM;
        toolbar = new JToolBar();
        toolbar.setRollover(true);

        // Add a Button to open instant a compatible Data
        openButton = new JButton();
        saveButton = new JButton();
        mainMenuButton = new JButton();
        try {
            Image fileOpenIcon = ImageIO.read(getClass().getResource("_004_OpenIcon.png"));
            BufferedImage oBI = createResizedCopy(fileOpenIcon, 30,30,false);
            openButton.setIcon(new ImageIcon(oBI));
            openButton.setToolTipText("Open new Animation");


            Image fileSaveIcon = ImageIO.read(getClass().getResource("_005_SafePic.jpg"));
            BufferedImage sBI = createResizedCopy(fileSaveIcon, 30,30,false);
            saveButton.setIcon(new ImageIcon(sBI));
            saveButton.setToolTipText("Save Current Animation");

            Image mainWindowsBack = ImageIO.read(getClass().getResource("_11_homeButton.png"));
            BufferedImage mWB = createResizedCopy(mainWindowsBack, 30,30,false);
            mainMenuButton.setIcon(new ImageIcon(mWB));
            mainMenuButton.setToolTipText("Back to Main Menu");

        } catch (IOException e) {
            e.printStackTrace();
        }

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openFILE();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
              //  AnimationImporter.importAnimation();


            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstElement = false;
                String NameFile = "AnimationCodeCheck";
                ArrayList< String > Text = new ArrayList< String >();

                Text.add(mAP.getAnimalScript());

                SaveFile(NameFile, Text);

               /** saveFile();
                currentAnimation =  mAP.getAnimation();
                if(currentAnimation != null) {
                    AnimationExporter.exportAnimation(currentAnimation);
                }
             else {
                    System.out.print("ERRROOOORRR");
            }
**/
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    guigo.setWindow();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }


            }
        });

        toolbar.add(openButton);

        // Add a Button to save instant a compatible Data

        toolbar.add(saveButton);
        toolbar.add(mainMenuButton);
        toolbar.addSeparator();
        fenster.getContentPane().add(toolbar,BorderLayout.NORTH);
    }

    private void SaveFile(String nameFile, ArrayList<String> text) {

        String path = "/Users/deniz/" + nameFile + ".txt";

        File file1 = new File(path);

        try {

            if (!file1.exists()) {

                file1.createNewFile();
            }


            File[] files = file1.listFiles();


            FileWriter fw = new FileWriter(file1, true);

            BufferedWriter bw = new BufferedWriter(fw);

            String s = String.valueOf(mM.getCurAnimNumber());
            bw.write(s);
            bw.newLine();

            bw.write("{");
            bw.newLine();

            for (int i = 0; i < text.size(); i++) {

                bw.write(text.get(i));
                bw.newLine();
            }

            bw.close();
            fw.close();

            FileReader fr = new FileReader(file1);

            BufferedReader br = new BufferedReader(fr);

            fw = new FileWriter(file1, true);

            bw = new BufferedWriter(fw);



        /**  while (br.ready()) {

              String line = br.readLine();

                System.out.println(line);

                bw.write(line);
                bw.newLine();
            }**/
            br.close();
            fr.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error in" + ex);
        }

    }


    public void openFILE() throws FileNotFoundException {


        ArrayList<String> ls = new ArrayList<String>();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String content = new Scanner(selectedFile).useDelimiter("\\Z").next();
            String currentAnimation = String.valueOf(content.charAt(0));
            System.out.println(content);

            StringBuilder sb = new StringBuilder();
            sb.append(content);
            sb.deleteCharAt(0);
            content = sb.toString();

            upperHalf.removeAll();
            upperHalf = mM.getAC();

            //upperHalf=  mM.getAnim().getMainWindow().getWindowCoordinator().getAnimationWindow(true).getAnimationCanvas();
            upperHalf.revalidate();
           sp1.removeAll();
            mpane.setThePane(upperHalf);
           sp1.add(upperHalf);
            upperHalf.repaint();
          sp1.repaint();

            System.out.print("HERE");

            //String stackCode = mpane.generateStackCode(fenster,true);
            mM.setAnimationCode(content);

            if(currentAnimation.equals("1")){
                mpane.setToolBarForQueue(this,fenster,"Stack",mM,ls);
            }else if(currentAnimation.equals("2")){
                mpane.setToolBarForQueue(this,fenster,"Ringbuffer",mM,ls);
            }else if(currentAnimation.equals("3")){
                mpane.setToolBarForQueue(this,fenster,"Queue",mM,ls);
            }

            mM.runDemo();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

        }
    }

    BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha){
        System.out.println("resizing ...");
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaleBI = new BufferedImage(scaledWidth,scaledHeight, imageType);
        Graphics2D g = scaleBI.createGraphics();
        if(preserveAlpha){
            g.setComposite(AlphaComposite.Src);
        }

        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaleBI;


    }

    public void setMYPane(JPanel jp, ScrollPane jsp){
        this.upperHalf = jp;
        this.sp1 = jsp;
    }

    public void setMainWindow(GUIGO gg){
        this.guigo = gg;
    }


  public void addButton(JFrame fenster,JButton j, String pic, String tip){
      try {
      Image fileSaveIcon = ImageIO.read(getClass().getResource(pic));
      BufferedImage sBI = createResizedCopy(fileSaveIcon, 30,30,false);
      j.setIcon(new ImageIcon(sBI));
      j.setToolTipText(tip);
      } catch (IOException e) {
          e.printStackTrace();
      }
      toolbar.add(j);
      fenster.getContentPane().add(toolbar,BorderLayout.NORTH);
      fenster.revalidate();
      fenster.repaint();
  }

  public void setMyp(MyPane mp){
      this.mpane = mp;
  }

  public JToolBar getBar(){return  toolbar;}

    public void setGUI(Gui g){
        this.gui = g;

    }


    public static void main(String[] args){}

}
