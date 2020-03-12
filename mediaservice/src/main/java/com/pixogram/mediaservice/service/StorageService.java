package com.pixogram.mediaservice.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	// private final Path rootLocation = Paths.get("upload-dir");
	private final Path rootLocation = Paths.get("src/main/resources/static");
	public void store(MultipartFile file) {
		try {
			// create a new directory
			// File file_new = new File(this.rootLocation.getName(0).toString() + "/" + "");
			// file_new.mkdir();
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
			log.info("Uploaded Successfully");
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("FAIL!");
		}
	}



}