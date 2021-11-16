package co.edu.unbosque.util;

import org.springframework.context.ApplicationContext;

public class Bean {

	/**
	 * Gets the bean of the given class active in the application session
	 * 
	 * @param name
	 * @return the bean if exists
	 */
	public static Object getBean(Class<?> name) {
		ApplicationContext context = SpringContext.getAppContext();
		return context.getBean(name);
	}

}
