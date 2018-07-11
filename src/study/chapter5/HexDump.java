package study.chapter5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import depend.BinaryStdIn;

public class HexDump {
	 // Do not instantiate.
    private HexDump() { }

    /**
     * Reads in a sequence of bytes from standard input and writes
     * them to standard output using hexademical notation, k hex digits
     * per line, where k is given as a command-line integer (defaults
     * to 16 if no integer is specified); also writes the number
     * of bits.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    	try {
			System.setIn(new FileInputStream("src/data/words3.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        int bytesPerLine = 16;

        int i;
        for (i = 0; !BinaryStdIn.isEmpty(); i++) {
            if (bytesPerLine == 0) {
                BinaryStdIn.readChar();
                continue;
            }
            if (i == 0) System.out.printf("");
            else if (i % bytesPerLine == 0) System.out.printf("\n", i);
            else System.out.print(" ");
            char c = BinaryStdIn.readChar();
            System.out.printf("%02x", c & 0xff);
        }
        if (bytesPerLine != 0) System.out.println();
        System.out.println((i*8) + " bits");
    }
}
