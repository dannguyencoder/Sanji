package com.loas.model;

import org.springframework.web.multipart.MultipartFile;

public class CSVFile {
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}
