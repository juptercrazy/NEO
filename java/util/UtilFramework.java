package util;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UtilFramework implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static ThreadLocal<Long> treadLocal = new ThreadLocal<Long>();
	
	public synchronized static ThreadLocal<Long> getThreadLocal() {
		return treadLocal;
	}

}
