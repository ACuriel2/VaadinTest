package com.example.test;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CsdWindowBuilder {
	
	private static Window csdWindow;
	
	public static Window build() {
		csdWindow = new Window("Carga un nuevo certificado de sello digital");
		
		csdWindow.setWidth("800px");
		csdWindow.setHeight("400px");
		csdWindow.setResizable(false);
		csdWindow.setModal(true);
		csdWindow.setDraggable(false);
				
		FileUploader receiver = new FileUploader();		
		Upload upload = new Upload("Carga archivo", receiver);
		upload.setButtonCaption("Start Upload");
		upload.addSucceededListener(receiver);
		
		FormLayout subWindowLayout = new FormLayout();
		subWindowLayout.addComponent(upload);
		
		
		
		csdWindow.setContent(subWindowLayout);
		csdWindow.center();
		return csdWindow;
	}

}
