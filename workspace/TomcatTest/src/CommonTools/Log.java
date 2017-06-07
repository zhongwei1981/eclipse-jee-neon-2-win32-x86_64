package CommonTools;

public class Log {
	private String _strURI = "";
	private static long _sLine = 0;

	public Log(String strURI) {
		_strURI = strURI;
	}

	public void d(String str, Object... args) {
		String prefix = String.format("[%d - Debug] %s: ", _sLine++, _strURI);
    	System.out.println(String.format(prefix + str, args));
    }
	
	public void e(String str, Object... args) {
		String prefix = String.format("[%d - ERROR] %s: ", _sLine++, _strURI);
    	System.out.println(String.format(prefix + str, args));
    }
	
	public void e(Exception e) {
		e(e.toString());
		e.printStackTrace();
	}
}
