package com.example.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class FileUploader implements Receiver, SucceededListener{
	public File file;
	
	public OutputStream receiveUpload(String fileName, String mimeType){
		// Create upload stream
		FileOutputStream fos = null; // Stream to write to
		try {
			// Open the file for writing.
			file = new File("/tmp/uploads/" + fileName);
			fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {
			new Notification("Could not open file<br/>",
					e.getMessage(),
					Notification.Type.ERROR_MESSAGE)
			.show(Page.getCurrent());
			return null;
		}
		return fos; // Return the output stream to write to				
	}

	public void uploadSucceeded(SucceededEvent event) {
		System.out.println(file.getName());		
	}
}
