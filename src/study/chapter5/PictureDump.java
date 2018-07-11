package study.chapter5;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import depend.BinaryStdIn;
import depend.Picture;

public class PictureDump {
	// Do not instantiate.
    private PictureDump() { }

    /**
     * Reads in a sequence of bytes from standard input and draws
     * them to standard drawing output as a width-by-height picture,
     * using black for 1 and white for 0 (and red for any leftover
     * pixels).
     *
     * @param args the command-line arguments
     */
    public static void pictureDump(String name) {
    	try {
			System.setIn(new FileInputStream(name));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int width = 16;
        int height = 6;
        Picture picture = new Picture(width, height);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (!BinaryStdIn.isEmpty()) {
                    boolean bit = BinaryStdIn.readBoolean();
                    if (bit) picture.set(col, row, Color.BLACK);
                    else     picture.set(col, row, Color.WHITE);
                }
                else {
                    picture.set(col, row, Color.RED);
                }
            }
        }
        picture.show();
    }
    public static void main(String[] args) {
    	pictureDump("src/data/words3.txt");
		
    }
}
