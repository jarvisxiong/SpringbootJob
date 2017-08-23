
/**
 * 
 */
package com.sistic.be.configuration.webmvcconfigurer;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author jianhong
 * NOTE do not annotate this with @EnableWebMvc, if you want to keep Spring Boots auto configuration for mvc.
 * Refer to https://www.keycdn.com/support/cache-control/ for more information on cache headers
 *
 */

@Configuration
@ConfigurationProperties(prefix = "spring.customresource")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
//	@Autowired
//	SessionValidatorInterceptor sessionValidatorInterceptor;
	
	private static String locationBin = "";
	private static String locationCss = "";
	private static String locationFonts = "";
	private static String locationImages = "";
	private static String locationJs = "";
	private static String locationAssets = "";
	private static String locationPublic = "";
	private static String locationProperties = "";
	private static String locationTenant = "";
	
	private static Integer cache_period = 0;
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(sessionValidatorInterceptor);
//	}
		
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/test/**").addResourceLocations(locationAssets).setCachePeriod(cache_period);
		registry.addResourceHandler("/bin/**").addResourceLocations(locationBin).setCachePeriod(cache_period).setCacheControl(CacheControl.maxAge(cache_period, TimeUnit.SECONDS).cachePrivate().noTransform());
		registry.addResourceHandler("/css/**").addResourceLocations(locationCss).setCachePeriod(cache_period).setCacheControl(CacheControl.maxAge(cache_period, TimeUnit.SECONDS).cachePrivate().noTransform());
		registry.addResourceHandler("/fonts/**").addResourceLocations(locationFonts).setCachePeriod(cache_period).setCacheControl(CacheControl.maxAge(cache_period, TimeUnit.SECONDS).cachePublic().noTransform());
		registry.addResourceHandler("/images/**").addResourceLocations(locationImages).setCachePeriod(cache_period).setCacheControl(CacheControl.maxAge(cache_period, TimeUnit.SECONDS).cachePublic().noTransform());
		registry.addResourceHandler("/js/**").addResourceLocations(locationJs).setCachePeriod(cache_period).setCacheControl(CacheControl.maxAge(cache_period, TimeUnit.SECONDS).cachePublic().noTransform());
		registry.addResourceHandler("/assets/**").addResourceLocations(locationAssets).setCachePeriod(cache_period).setCacheControl(CacheControl.maxAge(cache_period, TimeUnit.SECONDS).cachePrivate().noTransform());
		registry.addResourceHandler("/public/**").addResourceLocations(locationPublic).setCachePeriod(cache_period).setCacheControl(CacheControl.maxAge(cache_period, TimeUnit.SECONDS).cachePublic().noTransform());
		registry.addResourceHandler("/properties/**").addResourceLocations(locationProperties).setCachePeriod(cache_period).setCacheControl(CacheControl.maxAge(cache_period, TimeUnit.SECONDS).cachePrivate().noTransform());
		registry.addResourceHandler("/tenant/**").addResourceLocations(locationTenant).setCachePeriod(cache_period).setCacheControl(CacheControl.maxAge(cache_period, TimeUnit.SECONDS).cachePrivate().noTransform());
	}

	/**
	 * @return the locationFonts
	 */
	public static String getLocationFonts() {
		return locationFonts;
	}



	/**
	 * @param locationFonts the locationFonts to set
	 */
	public static void setLocationFonts(String locationFonts) {
		WebMvcConfig.locationFonts = locationFonts;
	}



	/**
	 * @return the locationBin
	 */
	public static String getLocationBin() {
		return locationBin;
	}

	/**
	 * @param locationBin the locationBin to set
	 */
	public static void setLocationBin(String locationBin) {
		WebMvcConfig.locationBin = locationBin;
	}

	/**
	 * @return the locationCss
	 */
	public static String getLocationCss() {
		return locationCss;
	}

	/**
	 * @param locationCss the locationCss to set
	 */
	public static void setLocationCss(String locationCss) {
		WebMvcConfig.locationCss = locationCss;
	}

	/**
	 * @return the locationImages
	 */
	public static String getLocationImages() {
		return locationImages;
	}

	/**
	 * @param locationImages the locationImages to set
	 */
	public static void setLocationImages(String locationImages) {
		WebMvcConfig.locationImages = locationImages;
	}

	/**
	 * @return the locationJs
	 */
	public static String getLocationJs() {
		return locationJs;
	}

	/**
	 * @param locationJs the locationJs to set
	 */
	public static void setLocationJs(String locationJs) {
		WebMvcConfig.locationJs = locationJs;
	}

	/**
	 * @return the locationAssets
	 */
	public static String getLocationAssets() {
		return locationAssets;
	}

	/**
	 * @param locationAssets the locationAssets to set
	 */
	public static void setLocationAssets(String locationAssets) {
		WebMvcConfig.locationAssets = locationAssets;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getLocationPublic() {
		return locationPublic;
	}

	/**
	 * 
	 * @param locationPublic
	 */
	public static void setLocationPublic(String locationPublic) {
		WebMvcConfig.locationPublic = locationPublic;
	}

	/**
	 * @return the cache_period
	 */
	public static Integer getCache_period() {
		return cache_period;
	}

	/**
	 * @param cache_period the cache_period to set
	 */
	public static void setCache_period(Integer cache_period) {
		WebMvcConfig.cache_period = cache_period;
	}

	/**
	 * 
	 * @return locationProperties
	 */
	public static String getLocationProperties() {
		return locationProperties;
	}

	/**
	 * 
	 * @param locationProperties
	 */
	public static void setLocationProperties(String locationProperties) {
		WebMvcConfig.locationProperties = locationProperties;
	}

	/**
	 * 
	 * @return locationTenant
	 */
	public static String getLocationTenant() {
		return locationTenant;
	}

	/**
	 * 
	 * @param locationTenant
	 */
	public static void setLocationTenant(String locationTenant) {
		WebMvcConfig.locationTenant = locationTenant;
	}
	
	

}
