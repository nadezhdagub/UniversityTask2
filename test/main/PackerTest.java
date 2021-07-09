import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;


public class PackerTest extends FileTest{
	@Test
	public void zip1() throws Exception {
		String[] command = "pack-rle -z txt/input/empty.txt -out txt/output/empty.txt".split(" ");
		Main.main(command);
		String out = "txt/output/empty.txt";
		assertTrue(fileExist(out) &&
				filesEquals("", out));
		Files.delete(Paths.get(out));
	}
	@Test
	public void zip2() throws Exception {
		String[] command = "pack-rle -z txt/input/test1.txt -out txt/output/test1.txt".split(" ");
		Main.main(command);
		String out = "txt/output/test1.txt";
		assertTrue(fileExist(out) &&
				filesEquals("-I used to be an adventurer like you," +
						" then I t耂o\u0006k an a耂r\fow in the kn耂e\u0001.", out));
		Files.delete(Paths.get(out));
	}
}
