package packer;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Packer {

	public static void compress(String inputName, String outputName) throws Exception {
		try (FileReader reader = new FileReader(inputName)) {
			FileWriter writer = new FileWriter(outputName);
			Scanner readLine = new Scanner(reader);
			while (readLine.hasNextLine()) {
				writer.write(compressLine(readLine.nextLine()));
				if (readLine.hasNextLine())
					writer.write("\n");
			}
			writer.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Compression complete!");
	}

	private static short[] charToShort(char[] list) {
		short[] result = new short[list.length];
		for (int i = 0; i < list.length; i++) {
			result[i] = (short) list[i];
		}
		return result;
	}

	private static String compressLine(String line) {
		if (line.length() <= 1) return line;
		ArrayList<Short> compressed = new ArrayList<>();
		ArrayList<Short> temp = new ArrayList<>();
		short[] inStr = charToShort(line.toCharArray());
		int index = 0;
		while (index != inStr.length) {
			short counter = 1;
			temp.clear();
			temp.add(inStr[index]);
			index++;
			if (index == inStr.length) {
				if (!compressed.get(compressed.size() - 1).equals(temp.get(0))) {
					compressed.add((short) 1);
					compressed.addAll(temp);
				}
				break;
			}
			if (inStr[index] == temp.get(temp.size() - 1)) {
				do {
					if (counter == 0x7fff) {
						compressed.add((short) (0x8000 + counter)); //Short.MAX_VALUE + 1
						compressed.addAll(temp);
						counter = 0;
					}
					index++;
					counter++;
					if (index == inStr.length)
						break;
				} while (inStr[index] == temp.get(temp.size() - 1));
				compressed.add((short) (0x8000 + counter)); //Short.MAX_VALUE + 1

			} else {
				do {
					if (counter == 0x7fff) {
						compressed.add(counter);
						compressed.addAll(temp);
						short a = temp.get(temp.size() - 1);
						temp.clear();
						temp.add(a);
						counter = 1;
					}
					temp.add(inStr[index]);
					index++;
					counter++;
					if (index == inStr.length)
						break;
				} while (inStr[index] != temp.get(temp.size() - 1));
				index--;
				if (index != inStr.length - 1) {
					temp.remove(temp.size() - 1);
					compressed.add(--counter);
				} else {
					compressed.add(counter);
				}

			}
			compressed.addAll(temp);
		}
		StringBuilder result = new StringBuilder();
		for (Short symb : compressed) {
			result.append((char) ((short) symb));
		}
		return result.toString();
	}
}