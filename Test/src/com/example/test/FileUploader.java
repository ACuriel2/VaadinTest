package com.example.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.cert.X509Certificate;

import com.tralix.sat.validator.utils.CertificateUtils;
import com.tralix.sat.validator.utils.exceptions.CertificateException;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class FileUploader implements Receiver, SucceededListener {

	private CsdBeanUpdater updater;

	public FileUploader(final CsdBeanUpdater updter) {
		updater = updter;
	}

	public File file;

	@Override
	public OutputStream receiveUpload(final String fileName, final String mimeType) {
		FileOutputStream fos = null;
		try {
			file = new File("/tmp/uploads/" + fileName);
			fos = new FileOutputStream(file);

		} catch (final java.io.FileNotFoundException e) {
			new Notification("No se pudo abrir el archivo <br/>", e.getMessage(), Notification.Type.ERROR_MESSAGE)
					.show(Page.getCurrent());
			return null;
		}
		return fos;
	}

	@Override
	public void uploadSucceeded(final SucceededEvent event) {
		System.out.println(file.getPath() + file.getName());
		try {
			X509Certificate certificate = CertificateUtils.getX509Certificate("");
			// certificate.get
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
