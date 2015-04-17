package com.example.test;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;

public class CsdWindowBuilder {
	
	private static Window csdWindow;
	
	public static Window build() {
		createSubWindow();					
		return csdWindow;
	}

	private static FormLayout getWindowLayout() {
		FormLayout subWindowLayout = new FormLayout();
		subWindowLayout.setMargin(true);		
		setWindowLayoutContent(subWindowLayout);	
		return subWindowLayout;
	}

	private static void setWindowLayoutContent(FormLayout subWindowLayout) {
		subWindowLayout.addComponent(getCerUploader());
		subWindowLayout.addComponent(getKeyUploader());
		subWindowLayout.addComponent(getPasswordField());
		subWindowLayout.addComponent(getButtonsLayout());
	}

	private static HorizontalLayout getButtonsLayout() {
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		setButtonsLayoutContent(buttonsLayout);
		buttonsLayout.setWidth(100, Unit.PERCENTAGE);
		buttonsLayout.setComponentAlignment(buttonsLayout.getComponent(0), Alignment.MIDDLE_RIGHT);
		return buttonsLayout;
	}

	private static void setButtonsLayoutContent(HorizontalLayout buttonsLayout) {
		buttonsLayout.addComponent(new Button("Cancelar"));
		buttonsLayout.addComponent(new Button("Guardar"));
	}

	private static PasswordField getPasswordField() {
		PasswordField passwordInput = new PasswordField();
		passwordInput.setCaption("Password");
		return passwordInput;
	}

	private static void createSubWindow() {
		csdWindow = new Window("Carga un nuevo certificado de sello digital");		
		setWindowProperties();
		csdWindow.setContent(getWindowLayout());
	}

	private static void setWindowProperties() {
		csdWindow.setWidth("800px");
		csdWindow.setHeight("400px");
		csdWindow.setResizable(false);
		csdWindow.setModal(true);
		csdWindow.setDraggable(false);
		csdWindow.setStyleName("csdWindow");
		csdWindow.center();
	}

	private static Upload getKeyUploader() {
		FileUploader keyReceiver = new FileUploader();	
		Upload keyUpload = new Upload("Llave pública (cer)", keyReceiver);
		keyUpload.addSucceededListener(keyReceiver);
		return keyUpload;
	}
	
	private static Upload getCerUploader(){
		FileUploader cerReceiver = new FileUploader();	
		Upload cerUpload = new Upload("Certificado (cer)", cerReceiver);
		cerUpload.addSucceededListener(cerReceiver);
		return cerUpload;
	}
}
