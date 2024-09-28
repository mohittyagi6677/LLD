package bike.rental.util;

public class LoggerUtil {
	    public static void log(Object... args) {
	        StringBuilder sb = new StringBuilder();
	        for (Object arg : args) {
	            sb.append(arg).append(" ");
	        }
	        System.out.println(sb.toString());
	    }

}
