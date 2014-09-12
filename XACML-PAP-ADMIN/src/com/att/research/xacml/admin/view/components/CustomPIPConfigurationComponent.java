/*
 *                        AT&T - PROPRIETARY
 *          THIS FILE CONTAINS PROPRIETARY INFORMATION OF
 *        AT&T AND IS NOT TO BE DISCLOSED OR USED EXCEPT IN
 *             ACCORDANCE WITH APPLICABLE AGREEMENTS.
 *
 *          Copyright (c) 2014 AT&T Knowledge Ventures
 *              Unpublished and Not for Publication
 *                     All Rights Reserved
 */
package com.att.research.xacml.admin.view.components;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.att.research.xacml.admin.jpa.PIPConfiguration;
import com.att.research.xacml.admin.jpa.PIPResolver;
import com.att.research.xacml.admin.view.events.FormChangedEventListener;
import com.att.research.xacml.admin.view.events.FormChangedEventNotifier;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Buffered.SourceException;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CustomPIPConfigurationComponent extends CustomComponent implements FormChangedEventNotifier {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private PIPParameterComponent pipParameterComponent;
	@AutoGenerated
	private TextField textFieldClassname;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final EntityItem<?> entity;
	private static final Log logger	= LogFactory.getLog(CustomPIPConfigurationComponent.class);
	private final CustomPIPConfigurationComponent self = this;
	private final BasicNotifier notifier = new BasicNotifier();
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 * @param configParamField 
	 */
	public CustomPIPConfigurationComponent(EntityItem<PIPConfiguration> entityConfig) {
		//
		// Save
		//
		this.entity = entityConfig;
		
		buildMainLayout();
		setCompositionRoot(mainLayout);
		//
		// InitializeGUI
		//
		this.initialize();
	}
	
	protected void initialize() {
		if (logger.isDebugEnabled()) {
			logger.debug("initializing " + this.entity.getEntity().toString());
		}
		this.initializeEntity();
		this.initializeText();
	}
	
	protected void initializeEntity() {
		//
		// If the entity is persisted, then we are editing
		// it. So don't remove anything.
		//
		if (this.entity.isPersistent()) {
			return;
		}
		//
		// Not sure if we really should remove stuff. To be
		// determined later.
		//
	}
	
	protected void initializeText() {
		this.textFieldClassname.setRequired(true);
		this.textFieldClassname.setRequiredError("You must declare the Java classname for the PIP Factory Loader");
		this.textFieldClassname.setImmediate(true);
		final Object entity = this.entity.getEntity();
		if (entity instanceof PIPConfiguration) {
			this.textFieldClassname.setValue(((PIPConfiguration)entity).getClassname());
		} else if (entity instanceof PIPResolver) {
			this.textFieldClassname.setValue(((PIPResolver)entity).getClassname());
		}
		//
		// Respond to events
		//
		this.textFieldClassname.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (entity instanceof PIPConfiguration) {
					((PIPConfiguration) entity).setClassname(self.textFieldClassname.getValue());
				} else if (entity instanceof PIPResolver) {
					((PIPResolver) entity).setClassname(self.textFieldClassname.getValue());
				}
				self.fireFormChangedEvent();
			}
		});
	}

	public void validate() throws InvalidValueException {
		if (logger.isDebugEnabled()) {
			logger.debug("validate");
		}
		this.textFieldClassname.validate();
	}
	
	public void commit() throws SourceException, InvalidValueException {
		if (logger.isDebugEnabled()) {
			logger.debug("commit");
		}
		this.textFieldClassname.commit();
	}

	public void discard() throws SourceException {
		if (logger.isDebugEnabled()) {
			logger.debug("discard");
		}
	}

	@Override
	public boolean addListener(FormChangedEventListener listener) {
		return this.notifier.addListener(listener);
	}

	@Override
	public boolean removeListener(FormChangedEventListener listener) {
		return this.notifier.removeListener(listener);
	}

	@Override
	public void fireFormChangedEvent() {
		this.notifier.fireFormChangedEvent();
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// textFieldClassname
		textFieldClassname = new TextField();
		textFieldClassname.setCaption("Java Classname");
		textFieldClassname.setImmediate(false);
		textFieldClassname
				.setDescription("Java classname of the code implementing the custom PIP.");
		textFieldClassname.setWidth("-1px");
		textFieldClassname.setHeight("-1px");
		textFieldClassname.setInputPrompt("Eg. com.foo.MyPIP");
		mainLayout.addComponent(textFieldClassname);
		mainLayout.setExpandRatio(textFieldClassname, 1.0f);
		
		// pipParameterComponent
		pipParameterComponent = new PIPParameterComponent(this.entity.getEntity());
		pipParameterComponent.setImmediate(false);
		pipParameterComponent.setWidth("-1px");
		pipParameterComponent.setHeight("-1px");
		mainLayout.addComponent(pipParameterComponent);
		
		return mainLayout;
	}
}
