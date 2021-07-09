import packer.Packer;
import unpacker.Unpacker;

public class Main {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Wrong input!");
			return;
		}
		if (args.length == 1 && args[0].equals("pack-rle")) {
			System.out.println("Usage: pack-rle [-z|-u] input.txt [-out output.txt]");
			return;
		}
		if (args.length < 3 || !args[0].equals("pack-rle") ||
				(!args[1].equals("-z") && !args[1].equals("-u"))) {
			System.out.println("Wrong input!");
			return;
		}
		if (args.length == 5 && !args[3].equals("-out")) {
			System.out.println("Wrong input!");
			return;
		}
		String inputName, outputName = null;
		inputName = args[2];

		if (args.length == 5) {
			outputName = args[4];
		}

		if (args[1].equals("-z")) {
			if (outputName == null)
				outputName = "txt/output/c_" + args[2].split("/")[args[2].split("/").length - 1];
			Packer.compress(inputName, outputName);
		} else if (args[1].equals("-u")) {
			if (outputName == null)
				outputName = "txt/output/dc_" + args[2].split("/")[args[2].split("/").length - 1];
			Unpacker.decompress(inputName, outputName);
		}
	}
}