package TestExtjs_Servlet;

public class Log {
	private String _strURI = "";
	private static long _sLine = 0;

	Log(String strURI) {
		_strURI = strURI;
	}

	void d(String str, Object... args) {
		String prefix = String.format("[%d - Debug] %s: ", _sLine++, _strURI);
    	System.out.println(String.format(prefix + str, args));
    }
	
	void e(String str, Object... args) {
		String prefix = String.format("[%d - ERROR] %s: ", _sLine++, _strURI);
    	System.out.println(String.format(prefix + str, args));
    }
}
