import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertTrue;

public class UnpackerTest extends FileTest{
	@Test
	public void unpack1() throws Exception{
		String[] command = "pack-rle -u txt/output/c_empty.txt".split(" ");
		Main.main(command);
		String out = "txt/output/dc_c_empty.txt";
		assertTrue(fileExist(out) &&
				filesEquals("", out));
		Files.delete(Paths.get(out));
	}
	@Test
	public void unpack2() throws Exception{
		String[] command = "pack-rle -u txt/output/c_test1.txt".split(" ");
		Main.main(command);
		String out = "txt/output/dc_c_test1.txt";
		assertTrue(fileExist(out) &&
				filesEquals("I used to be an adventurer like you," +
						" then I took an arrow in the knee.", out));
		Files.delete(Paths.get(out));
	}
}
