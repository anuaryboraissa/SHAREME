package com.example.share.Entities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.web.multipart.MultipartFile;

public class FileAploadUtil {
 public static void saveFile(String uploadDir,String fileName,MultipartFile multipartFile) throws IOException
 {
	 Path uploadPath=Paths.get(uploadDir);
	 if (!Files.exists(uploadPath)) {
		Files.createDirectories(uploadPath);
	}
	 try(InputStream inputStream=multipartFile.getInputStream()) {
		 Path filePath=uploadPath.resolve(fileName);
		 System.out.println(filePath.toFile().getAbsolutePath());
		 Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		 System.out.println("saved");
		
	} catch (IOException e) {
		// TODO: handle exception
	  throw new IOException("could not save image file"+fileName,e);
	}
	
}
}
