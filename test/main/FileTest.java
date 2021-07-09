import java.io.File;
import java.nio.file.Files;
import java.util.List;

class FileTest {
	boolean fileExist(String name) {
		File file = new File(name);
		return file.exists();
	}

	boolean filesEquals(String in, String out) throws Exception {
		File file = new File(out);
		List<String> lines = Files.readAllLines(file.toPath());
		StringBuilder content = new StringBuilder();
		int counter = 1;
		for (String line : lines) {
			if (counter < lines.size()) {
				content.append(line).append("\n");
				counter++;
			} else {
				content.append(line);
			}
		}
		return in.equals(content.toString());
	}
}
