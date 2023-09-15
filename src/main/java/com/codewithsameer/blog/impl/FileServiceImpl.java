package com.codewithsameer.blog.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codewithsameer.blog.exceptions.ResourceNotFoundException;
import com.codewithsameer.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		System.out.println("Path--->"+path);

		// Folder bana liya

		File filee = new File(path);
		if (!filee.exists()) {
			filee.mkdir();
		}
		String original_name = file.getOriginalFilename();
		String randomId = UUID.randomUUID().toString();
		
		// creating random file Name
		String filename_random_name = randomId.concat(original_name.substring(original_name.lastIndexOf(".")));
		
		// Path where file will be copy

		String fullPath_to_copy = path + File.pathSeparator + filename_random_name;
				

		if (original_name.substring(original_name.lastIndexOf(".")).equalsIgnoreCase(".png")
				|| original_name.substring(original_name.lastIndexOf(".")).equalsIgnoreCase(".jpg")) {
			Files.copy(file.getInputStream(), Paths.get(fullPath_to_copy));
			
			
						
		} else
		{
			Integer a=0;
			throw  new ResourceNotFoundException("Not a JPEG or PNG format", "#Bad Image", a);

		}
		
		return filename_random_name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
