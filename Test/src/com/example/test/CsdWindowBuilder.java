package com.example.test;

import java.util.Date;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;

public class CsdWindowBuilder {

	private Window csdWindow;
	private Upload cerUpload;
	private CsdBeanUpdater updaterBean;

	public CsdWindowBuilder(final CsdBeanUpdater updater) {
		updaterBean = updater;
	}

	public Window build() {
		createSubWindow();
		return csdWindow;
	}

	private FormLayout getWindowLayout() {
		FormLayout subWindowLayout = new FormLayout();
		subWindowLayout.setMargin(true);
		setWindowLayoutContent(subWindowLayout);
		return subWindowLayout;
	}

	private void setWindowLayoutContent(final FormLayout subWindowLayout) {
		subWindowLayout.addComponent(getCerUploader());
		subWindowLayout.addComponent(getKeyUploader());
		subWindowLayout.addComponent(getPasswordField());
		subWindowLayout.addComponent(getButtonsLayout());
	}

	private HorizontalLayout getButtonsLayout() {
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		setButtonsLayoutContent(buttonsLayout);
		buttonsLayout.setWidth(100, Unit.PERCENTAGE);
		buttonsLayout.setComponentAlignment(buttonsLayout.getComponent(0), Alignment.MIDDLE_RIGHT);
		return buttonsLayout;
	}

	private void setButtonsLayoutContent(final HorizontalLayout buttonsLayout) {
		buttonsLayout.addComponent(getCancelButton());
		buttonsLayout.addComponent(getSaveButton());
	}

	private Button getSaveButton() {
		Button saveButton = new Button("Guardar");
		setSaveButtonEvent(saveButton);
		return saveButton;
	}

	private void setSaveButtonEvent(final Button saveButton) {
		saveButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(final ClickEvent event) {
				updaterBean.updateTable(new CsdBean("Tet1", new Date(), new Date()));
			}
		});
	}

	private Button getCancelButton() {
		Button cancelButton = new Button("Cancelar");
		setCancelButtonEvent(cancelButton);
		return cancelButton;
	}

	private void setCancelButtonEvent(final Button cancelButton) {
		cancelButton.addClickListener(getCancelButtonEvent());
	}

	private ClickListener getCancelButtonEvent() {
		return new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				csdWindow.close();
			}
		};
	}

	private PasswordField getPasswordField() {
		PasswordField passwordInput = new PasswordField();
		passwordInput.setCaption("Password");
		return passwordInput;
	}

	private void createSubWindow() {
		csdWindow = new Window("Carga un nuevo certificado de sello digital");
		setWindowProperties();
		csdWindow.setContent(getWindowLayout());
	}

	private void setWindowProperties() {
		csdWindow.setWidth("800px");
		csdWindow.setHeight("400px");
		csdWindow.setResizable(false);
		csdWindow.setModal(true);
		csdWindow.setDraggable(false);
		csdWindow.setStyleName("csdWindow");
		csdWindow.center();
	}

	private Upload getKeyUploader() {
		FileUploader keyReceiver = new FileUploader(updaterBean);
		Upload keyUpload = new Upload("Llave pública (key)", keyReceiver);
		keyUpload.addSucceededListener(keyReceiver);
		return keyUpload;
	}

	private Upload getCerUploader() {
		FileUploader cerReceiver = new FileUploader(updaterBean);
		cerUpload = new Upload("Certificado (cer)", cerReceiver);
		cerUpload.addSucceededListener(cerReceiver);
		return cerUpload;
	}
}
