package com.component.load;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomResourceLoader {
	public void run4(){
		DefaultResourceLoader loader = new DefaultResourceLoader();
		ClassPathResource resource = new ClassPathResource("resource/redis.properties");

		// Resource rc = loader.getResource("classpath*:resource/redis.properties");
		File f;
		try {
			Path path = Paths.get(resource.getURI());
			System.out.println(path.getRoot());
			System.out.println(path.toAbsolutePath());
			f = path.toFile();

			// System.out.println(rc.getURI());
		}catch (Exception e){
			System.out.println("Exception !!");
		}

	}
}
