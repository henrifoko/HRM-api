package com.frsummit.HRM.api.model;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import com.frsummit.HRM.api.config.Constances;

public class TempFileCleanerThread extends Thread {

	private String fileName;

	public TempFileCleanerThread(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void run() {
		while (!this.deleteTempFile(fileName)) {
			try {
				Thread.sleep(Constances.TIMEOUT_CHECK);
				System.out.println("Deleting the temp file [" + fileName + "]... ");
				this.deleteTempFile(fileName);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method allows you to erase a temporary file from the disk. It uses the
	 * Files class of the nio API for this.
	 * 
	 * @param fileName - The name of the temporary file
	 */
	public boolean deleteTempFile(String fileName) {
		try {
			Files.deleteIfExists(Paths.get(fileName));
		} catch (NoSuchFileException e) { // already deleted
			// The file have already been removed
			System.out.println("No such file/directory exists");
			return true;
		} catch (DirectoryNotEmptyException e) { // already deleted
			// ...
			System.out.println("Directory is not empty.");
			return true;
		} catch (IOException e) { // already deleted
			// The program does not have enough privileges to open the file
			System.out.println("Invalid permissions.");
			return false;
		}

		System.out.println("Deletion successful.");
		return true;
	}
}
