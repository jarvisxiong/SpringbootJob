package com.sistic.be.app.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.sistic.be.configuration.ReaderConfig;

@Service
public class FileReaderService implements ResourceLoaderAware {

	@Autowired
	private ReaderConfig readerConfig;
	@Autowired
	private ResourceLoader resourceLoader;

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	public Resource getResource(String location) throws IOException {

		if (location.isEmpty()) {
			return null;
		}
		
		Resource resource = resourceLoader.getResource(readerConfig.getLocation() + location);

		return resource;
	}

}
