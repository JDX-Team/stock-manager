package com.jdx.common.config;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Message source for front development messages 
 *
 */
public class FrontResourceBundleMessageSource  extends ReloadableResourceBundleMessageSource {
	private static final String PROPERTIES_SUFFIX = ".properties";

	private PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	
	/**
	 * Override method for support loading i18n messages from multiple jars
	 */
	@Override
	protected PropertiesHolder refreshProperties(String filename, PropertiesHolder propHolder) {
	    if (filename.startsWith(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX)) {
	        return refreshClassPathProperties(filename, propHolder);
	    } else {
	        return super.refreshProperties(filename, propHolder);
	    }
	}
	
	private PropertiesHolder refreshClassPathProperties(String filename, PropertiesHolder propHolder) {
	    Properties properties = new Properties();
	    long lastModified = -1;
	    try {
	      Resource[] resources = resolver.getResources(filename + PROPERTIES_SUFFIX);
	      for (Resource resource : resources) {
	        String sourcePath = resource.getURI().toString().replace(PROPERTIES_SUFFIX, "");
	        PropertiesHolder holder = super.refreshProperties(sourcePath, propHolder);
	        properties.putAll(holder.getProperties());
	        if (lastModified < resource.lastModified())
	          lastModified = resource.lastModified();
	      }
	    } catch (IOException ignored) { 
	    }
	    return new PropertiesHolder(properties, lastModified);
	}

    @Override
    protected Properties loadProperties(Resource resource, String fileName) throws IOException {

        return super.loadProperties(resource, fileName);
    }

    /**
     * Gets all messages for presented Locale.
     * @param locale user request's locale
     * @return all messages
     */
    public Properties getMessages(Locale locale){
        return getMergedProperties(locale).getProperties();
    }
}
