package utils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LogUtils {
	private LogUtils() {}

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz.getName());
    }
}
