package com.example.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.sun.jna.platform.unix.X11.Font;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@Theme("test")
public class TestUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = TestUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final String LEGEND_TEXT = "Capture los datos fiscales con los que se encuentra dado de alta "+
								   "en el Registro Federal de Contribuyentes como emisor. \nEsta información "+
								   "será utilizada para generar sus Comprobantes Fiscales";
		final String INFO_FISCAL_TITLE = "Información Fiscal del emisor";
	    final String DOMICILIO_FISCAL_TITLE = "Domicilio Fiscal";
	    
		final VerticalLayout verticalBody = new VerticalLayout();
		verticalBody.setMargin(true);
		setContent(verticalBody);
		
		HorizontalLayout horizontalBody = new HorizontalLayout();
		verticalBody.addComponent(horizontalBody);		
		
		Panel configurationListContainer = new Panel("Configuración");
		configurationListContainer.setHeight(100, Sizeable.UNITS_PERCENTAGE);
		configurationListContainer.setWidth("250px");
		horizontalBody.addComponent(configurationListContainer);
		
		ListSelect configurationList = new ListSelect();
		configurationList.setSizeFull();
		configurationList.addItem("Datos Fiscales");
		
		configurationListContainer.setContent(configurationList);
		
		Panel fieldsContainer = new Panel("Datos Fiscales");
		horizontalBody.addComponent(fieldsContainer);
		
		VerticalLayout verticalFieldsContainer = new VerticalLayout();
		fieldsContainer.setContent(verticalFieldsContainer);
		/**/
		FormLayout editorLayout = new FormLayout();

        Label legentLabel = new Label(LEGEND_TEXT, ContentMode.PREFORMATTED);
        editorLayout.addComponent(legentLabel);

        Label infoFiscalEmisorTitle = new Label(INFO_FISCAL_TITLE);
        infoFiscalEmisorTitle.setStyleName("infoFiscalTitle");
        editorLayout.addComponent(infoFiscalEmisorTitle);
        
        FormLayout infoFieldLayout = new FormLayout();
        infoFieldLayout.setWidth("700px");
        editorLayout.addComponent(infoFieldLayout);

        for (String fieldName : getfiscalEmisorFields()) {
            TextField field = new TextField(fieldName);
            infoFieldLayout.addComponent(field);
            field.setWidth("100%");
        }

        Label domicilioFiscalTitle = new Label(DOMICILIO_FISCAL_TITLE);
        domicilioFiscalTitle.setStyleName("domFiscalTitle");
        editorLayout.addComponent(domicilioFiscalTitle);

        HorizontalLayout domicilioFiscalLayout = new HorizontalLayout();
        editorLayout.addComponent(domicilioFiscalLayout);

        FormLayout leftColumDomicilioFiscal = new FormLayout();
        leftColumDomicilioFiscal.setWidth(50, Sizeable.UNITS_PERCENTAGE);
        domicilioFiscalLayout.addComponent(leftColumDomicilioFiscal);

        for (String fieldName : getDomicilioFiscalLeftFields()) {
            TextField field = new TextField(fieldName);
            field.setWidth("400px");
            leftColumDomicilioFiscal.addComponent(field);            
        }
        
        HorizontalLayout blankSpace = new HorizontalLayout();
        blankSpace.setWidth("30px");
        domicilioFiscalLayout.addComponent(blankSpace);

        FormLayout rightColumDomicilioFiscal = new FormLayout();
        domicilioFiscalLayout.addComponent(rightColumDomicilioFiscal);

        for (String fieldName : getDomicilioFiscalRigthFields()) {
            TextField field = new TextField(fieldName);
            field.setWidth("400px");
            rightColumDomicilioFiscal.addComponent(field);            
        }
        
        HorizontalLayout newCSDButtonAndTextLayout = new HorizontalLayout();
        Label newCSDText = new Label("Certificado de sello digital");
        newCSDText.addStyleName("newCSDText");
        newCSDButtonAndTextLayout.addComponent(newCSDText);
        Button newCSDButton = new Button("Nuevo CSD");
        
        newCSDButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(CsdWindowBuilder.build());
			}
		});
        
        newCSDButton.setIcon(FontAwesome.PLUS);
        newCSDButtonAndTextLayout.addComponent(newCSDButton);
        
        editorLayout.addComponent(newCSDButtonAndTextLayout);
        
        Collection<CsdBean> fakeCsds = createFakeCsds();
        BeanItemContainer<CsdBean> beanContainer = new BeanItemContainer<CsdBean>(CsdBean.class, fakeCsds);
        Grid csdGrid = new Grid(beanContainer);
        csdGrid.setColumnOrder("numeroSerie", "validoDe", "validoHasta");
        csdGrid.setWidth("1000px");
        csdGrid.setHeight("200px");
        
        editorLayout.addComponent(csdGrid);
        
        CssLayout buttonsLayout = new CssLayout();
        buttonsLayout.setStyleName("buttonsLayout");
        Button saveButton = new Button("Guardar");        
        Button cancelButton = new Button("Cancelar");
        buttonsLayout.addComponent(cancelButton);
        HorizontalLayout buttonSpace = new HorizontalLayout();
        buttonSpace.setWidth("10px");
        buttonsLayout.addComponent(buttonSpace);
        buttonsLayout.addComponent(saveButton);
        
        editorLayout.addComponent(buttonsLayout);
        
        verticalFieldsContainer.addComponent(editorLayout);
	}
	
	private Collection<CsdBean> createFakeCsds() {
		Collection<CsdBean> collectionFake = new ArrayList<CsdBean>();
				collectionFake.add(new CsdBean("4545", new Date(), new Date()));
				collectionFake.add(new CsdBean("4546", new Date(), new Date()));
				collectionFake.add(new CsdBean("4547", new Date(), new Date()));
		return collectionFake;
	}

	private String[] getfiscalEmisorFields() {
        return new String[] {"RFC", "Razón Social", "Régimen Fiscal"};
    }

    private String[] getDomicilioFiscalLeftFields() {
        return new String[] {"Calle", "Número Exterior", "Número Interior", "Colonia", "Código Postal"};
    }

    private String[] getDomicilioFiscalRigthFields() {
        return new String[]{"Localidad", "Referencia", "Municipio", "Estado", "País"};
    }

}