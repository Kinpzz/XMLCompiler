package scanner;

/**
 * 16th Oct, 2016
 * @author x1057057 Yan Pengxiang
 */
public class MainTest {
	public static void main(String[] args) {
		// XML scanner singleton
		XMLScanner.getInstance().scan("./src/input.xml");
		System.out.print("Scan finished");
	}
}
