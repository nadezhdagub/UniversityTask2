package unpacker;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Unpacker {
	public static void decompress(String inputName, String outputName) {
		try (FileReader reader = new FileReader(inputName); FileWriter writer = new FileWriter(outputName)) {
			Scanner readLine = new Scanner(reader);
			while (readLine.hasNextLine()) {
				writer.write(decompressLine(readLine.nextLine()));
				if (readLine.hasNextLine())
					writer.write("\n");
			}
			writer.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Decompression complete!");
	}
	
	private static short[] charToShort(char[] list) {
		short[] result = new short[list.length];
		for (int i = 0; i < list.length; i++) {
			result[i] = (short) list[i];
		}
		return result;
	}

	private static String decompressLine(String line) {
		if (line.length() <= 1) return line;
		short[] inStr = charToShort(line.toCharArray());
		StringBuilder result = new StringBuilder();
		int index = 0;
		while (index != inStr.length) {
			if (inStr[index] < 0) {
				for (int i = 0; i < inStr[index] + Short.MAX_VALUE + 1; i++)
					result.append((char) inStr[index + 1]);
				index += 2;
			} else {
				for (int i = 0; i < inStr[index]; i++) {
					result.append((char) inStr[index + i + 1]);
				}
				index += inStr[index] + 1;
			}
		}
		return result.toString();
	}
}