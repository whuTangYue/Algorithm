package study.chapter5;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import depend.BinaryStdIn;

public class BinaryDump {
	// Do not instantiate.
    private BinaryDump() { }
    
    public static void binaryDump(String name) {
    	try {
			System.setIn(new FileInputStream(name));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        int bitsPerLine = 16;

        int count;
        for (count = 0; !BinaryStdIn.isEmpty(); count++) {
            if (bitsPerLine == 0) {
                BinaryStdIn.readBoolean();
                continue;
            }
            else if (count != 0 && count % bitsPerLine == 0) System.out.println();
            if (BinaryStdIn.readBoolean()) System.out.print(1);
            else                           System.out.print(0);
        }
        if (bitsPerLine != 0) System.out.println();
        System.out.println(count + " bits");
    }

    public static void main(String[] args) {
    	binaryDump("src/data/words3.txt");
		
    }
}
