package com.frsummit.HRM.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.model.TempFileCleanerThread;

@Service
public class FileService {

	/**
	 * This method is used to store temporary files while they are downloading by a
	 * client.
	 * 
	 * @param data - The byte content of the file
	 * @return The name of the file
	 */
	public String saveTempFile(byte[] data) {
		// The name of the temporary file
		int randInt = new Random().nextInt();
		String currentDate = new Date().toString();
		String fileName = currentDate + "_" + randInt;
		fileName = fileName.replaceAll(":", " ") + ".temp"; // TODO add file extension
		Path path = Paths.get(fileName);

		// Saving the file into the project repository
		try {
			Files.write(path, data);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		TempFileCleanerThread tfct = new TempFileCleanerThread(fileName);
		tfct.start();

		return fileName;
	}
}
