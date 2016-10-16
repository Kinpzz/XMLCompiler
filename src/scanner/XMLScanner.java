package scanner;
/**
 * 16th Oct, 2016
 * @author x1057057 Yan Pengxiang
 */
public class XMLScanner {
	
	// buffer read from file
    private StringBuffer buffer = new StringBuffer();
    // current scanning char of buffer
    private char ch;
    // index of current scanning char
    private int i = 0;
    // token
    private String strToken;
    // line number
    private int linenum = 1;
    // instance of XMLScanner singleton
    private static XMLScanner _instance;
    // instance of FileUtil
    private FileUtil fileUtil = FileUtil.getInstance();
   
    private XMLScanner() {}

    public static XMLScanner getInstance() {
    	if (_instance == null) {
    		_instance = new XMLScanner();
    	}
    	return _instance;
    }
    
    /**
     *  get next char
     */
    public void getChar() {
		ch = buffer.charAt(i);
		i++;
	}
    
    /**
     *  get next not white char except line separator
     */
    public void getBC() {
    	if (ch != '\n' && ch != '\r')
    		while(Character.isWhitespace(ch))
    			getChar();
    }
    /**
     *  cat current char to token
     */
    public void concat() {
        strToken += ch;
    }
    /**
     * retract to previous char
     */
    public void retract() {
        i--;
        ch = ' ';
    }
    /**
     * write one token on one line in the file
     * @param s - token to write
     */
    public void writeFile(String s) {
		fileUtil.writeFile(s+"\n");
	}
    /**
     * scan the file and output
     * @param fileSrc - the file to scan
     */
    public void scan(String fileSrc) {
    	fileUtil.readFile(buffer, fileSrc);
    	
        strToken = "";
        fileUtil.clearFile();
        while (i < buffer.length()) {
            getChar();
            getBC();
            if (ch == '<' || ch == '>' || ch == '?' ||
                ch == ':' || ch == '=' || ch == '/') {
                writeFile(ch+"");
            } else if (Character.isLetter(ch)) {
                while (Character.isLetter(ch) || Character.isDigit(ch) || ch == '-') {
                    concat();
                    getChar();
                }
                retract();
                
                writeFile(strToken);
                strToken = "";
            } else if (ch == '"') {
                getChar();
                while (ch != '"') {
                    concat();
                    getChar();
                }
                writeFile("\"");
                writeFile(strToken);
                writeFile("\"");
                strToken="";
            } else if (ch == '\n') {
                linenum++;
            } else {
                if (ch != '\r')
                	writeFile("error at line"+linenum);
            }
        }
    }
}
