package BachelorArbeitWs1617;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by deniz on 05.12.16.
 */
public class ConsoleOutput extends OutputStream {

    ArrayList<Byte> data = new ArrayList<Byte>();

    private JTextArea output;

    public ConsoleOutput(JTextArea output){
        this.output = output;

    }

    private void fireDataWritten(){

        int lines = 0;
        for(int i = 0; i < data.size();i++){
            byte b = data.get(i);

            if (b == 10){
                lines++;
            }

            if (lines >= 250) {
                data=(ArrayList<Byte>) data.subList(i, data.size());
            }
        }

        StringBuilder bldr = new StringBuilder();

        for(byte b:data){
            bldr.append((char) b);
        }


        output.setText(bldr.toString());
    }


    @Override
    public void write(int b) throws IOException {
        data.add((byte) b);

        fireDataWritten();

    }
}
