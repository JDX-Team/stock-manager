package com.jdx.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that allows to enable one fetch profile on a repository method
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableFecthProfile {

	/**
	 * Allow define an fetch profile to be enabled on execution time.
	 * 
	 * @return name of fetch profile to enable
	 */
	String value() default "";
}
