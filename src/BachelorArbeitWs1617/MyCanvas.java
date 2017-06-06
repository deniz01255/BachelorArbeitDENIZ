package BachelorArbeitWs1617;

import animal.graphics.PTGraphicObject;
import animal.gui.GraphicVector;
import animal.gui.GraphicVectorEntry;
import animal.main.AnimationCanvas;
import animal.main.AnimationCanvasMouseListener;
import animal.misc.ScalableGraphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by deniz on 21.03.17.
 */
public class MyCanvas extends AnimationCanvas {
    private static final long serialVersionUID = 5520674258993937191L;
    private Image image = null;
    private GraphicVector objects = null;
    private Dimension oldSize = new Dimension(0, 0);
    private Dimension referenceSize = this.initSize();
    private Dimension internalSize;
    private ScalableGraphics sg;
    private Color backgroundColor;
    private Image backgroundImage;
    private Dimension explicitSize;


    public MyCanvas() {
        this.internalSize = new Dimension(this.referenceSize);
        this.sg = new ScalableGraphics();
        this.backgroundColor = Color.WHITE;
        this.backgroundImage = null;
        this.explicitSize = null;
        this.addMouseListener(new AnimationCanvasMouseListener(this));
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Dimension size = null;
        this.internalSize = this.initSize();
        size = this.internalSize;
        Graphics ig = null;
        if(this.image == null || !size.equals(this.oldSize)) {
            this.image = this.createImage(this.internalSize.width, this.internalSize.height);
            this.oldSize = size;
        }

        if(this.image != null) {
            ig = this.image.getGraphics();
            if(this.getBackgroundImage() == null) {
                ig.setColor(this.backgroundColor);
                ig.fillRect(0, 0, size.width, size.height);
            } else {
                ig.drawImage(this.getBackgroundImage(), 0, 0, size.width, size.height, this.getBackgroundColor(), this);
            }

            Graphics where;
            if(this.sg.getMagnification() == 1.0D) {
                where = ig;
            } else {
                where = ig;
                if(ig instanceof Graphics2D) {
                    ((Graphics2D)ig).scale(this.sg.getMagnification(), this.sg.getMagnification());
                }
            }

            if(this.objects != null && this.objects.getSize() > 0) {
                int gveSize = this.objects.getSize();
                GraphicVectorEntry[] gves = this.objects.convertToArray();
                this.objects.heapsort(gves);

                for(int a = gveSize - 1; a >= 0; --a) {
                    if(!gves[a].isTemporary()) {
                        gves[a].go.paint(where);
                    }
                }
            }

            g.drawImage(this.image, 0, 0, this);
            ig.dispose();
        }

        this.setAnimationCanvasDimension(this);
    }

    @Override
    public void setAnimationCanvasDimension(AnimationCanvas canvas) {
        JLayeredPane panel = (JLayeredPane)canvas.getParent().getParent();
        byte widthOffset = 25;
        byte heightOffset = 25;
        int topLeftX = 2147483647;
        int topLeftY = 2147483647;
        int bottomRightX = -2147483648;
        int bottomRightY = -2147483648;
        int sizeDifferenzWidth;
        int height;
        if(canvas.getObjects() == null) {
            topLeftX = 0;
            topLeftY = 0;
            bottomRightX = 0;
            bottomRightY = 0;
        } else {
            GraphicVectorEntry[] sizeDifferenzHeight;
            height = (sizeDifferenzHeight = canvas.getObjects().convertToArray()).length;

            for(sizeDifferenzWidth = 0; sizeDifferenzWidth < height; ++sizeDifferenzWidth) {
                GraphicVectorEntry width = sizeDifferenzHeight[sizeDifferenzWidth];
                PTGraphicObject object = width.getGraphicObject();
                Rectangle rec = this.getRealBoundingBox(object, canvas);
                topLeftX = 0;
                topLeftY = 0;
                bottomRightX = rec.x + rec.width > bottomRightX?rec.x + rec.width:bottomRightX;
                bottomRightY = rec.y + rec.height > bottomRightY?rec.y + rec.height:bottomRightY;
            }
        }

        boolean var15 = false;
        sizeDifferenzWidth = panel.getWidth() - 8 - (widthOffset + bottomRightX - topLeftX);
        int var16;
        if(sizeDifferenzWidth >= 0) {
            var16 = panel.getWidth() - 8;
        } else {
            var16 = widthOffset + bottomRightX - topLeftX;
        }

        boolean var17 = false;
        int var18 = panel.getHeight() - 8 - (heightOffset + bottomRightY - topLeftY);
        if(var18 >= 0) {
            height =panel.getHeight() - 8;
        } else {
            height = heightOffset + bottomRightY - topLeftY;
        }

        if(var18 < 0 && sizeDifferenzWidth >= 8) {
            var16 -= 14;
        }

        if(sizeDifferenzWidth < 0 && var18 >= 8) {
            height -= 14;
        }

        canvas.setExplicitSize(new Dimension(var16, height));
        canvas.repaint();
        canvas.updateUI();
        panel.repaint();
        panel.getParent().repaint();
    }
}
