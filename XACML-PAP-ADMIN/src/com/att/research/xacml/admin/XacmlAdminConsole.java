/*
 *                        AT&T - PROPRIETARY
 *          THIS FILE CONTAINS PROPRIETARY INFORMATION OF
 *        AT&T AND IS NOT TO BE DISCLOSED OR USED EXCEPT IN
 *             ACCORDANCE WITH APPLICABLE AGREEMENTS.
 *
 *          Copyright (c) 2013 AT&T Knowledge Ventures
 *              Unpublished and Not for Publication
 *                     All Rights Reserved
 */
package com.att.research.xacml.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.att.research.xacml.admin.components.AttributeDictionary;
import com.att.research.xacml.admin.components.ObadviceDictionary;
import com.att.research.xacml.admin.components.PDPManagement;
import com.att.research.xacml.admin.components.PIPManagement;
import com.att.research.xacml.admin.components.PolicyWorkspace;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class XacmlAdminConsole extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Label labelCopyright;
	@AutoGenerated
	private TabSheet tabSheet;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private Label labelWelcome;
	@AutoGenerated
	private Label caption;
	@AutoGenerated
	private Embedded embedded_1;

	private static Log logger	= LogFactory.getLog(XacmlAdminConsole.class);
	
	private final PolicyWorkspace policyWorkspace;
	private final AttributeDictionary attributeDictionary;
	private final ObadviceDictionary obadvice;
	private final PDPManagement pdp;
	private final PIPManagement pip;
//	private final UserManagement user;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public XacmlAdminConsole() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Creating tabs...");
		}
		
		this.labelWelcome.setValue("Welcome " + ((XacmlAdminUI)UI.getCurrent()).getUserName());
		this.labelCopyright.setContentMode(ContentMode.HTML);
		
		if (((XacmlAdminUI)UI.getCurrent()).isAuthorized( 
				XacmlAdminAuthorization.AdminAction.ACTION_READ, 
				XacmlAdminAuthorization.AdminResource.RESOURCE_POLICY_WORKSPACE)) {
			this.policyWorkspace = new PolicyWorkspace();
			this.tabSheet.addComponent(this.policyWorkspace);
			this.tabSheet.getTab(this.policyWorkspace).setCaption("Policy Workspace");
		} else {
			this.policyWorkspace = null;
		}
		
		if (((XacmlAdminUI)UI.getCurrent()).isAuthorized( 
				XacmlAdminAuthorization.AdminAction.ACTION_READ, 
				XacmlAdminAuthorization.AdminResource.RESOURCE_DICTIONARIES)) {
			this.attributeDictionary = new AttributeDictionary();
			this.tabSheet.addComponent(this.attributeDictionary);
			this.tabSheet.getTab(this.attributeDictionary).setCaption("Attribute Dictionary");

			this.obadvice = new ObadviceDictionary(); 
			this.tabSheet.addComponent(this.obadvice);
			this.tabSheet.getTab(this.obadvice).setCaption("Obligation/Advice Dictionary");
		} else {
			this.attributeDictionary = null;
			this.obadvice = null;
		}
		if (((XacmlAdminUI)UI.getCurrent()).isAuthorized( 
									XacmlAdminAuthorization.AdminAction.ACTION_READ, 
									XacmlAdminAuthorization.AdminResource.RESOURCE_PDP_ADMIN)) {
			this.pdp = new PDPManagement(((XacmlAdminUI)UI.getCurrent()).getPAPEngine());
			this.tabSheet.addComponent(this.pdp);
			this.tabSheet.getTab(this.pdp).setCaption("PDP Management");
		} else {
			this.pdp = null;
		}
		
		if (((XacmlAdminUI)UI.getCurrent()).isAuthorized( 
				XacmlAdminAuthorization.AdminAction.ACTION_READ, 
				XacmlAdminAuthorization.AdminResource.RESOURCE_PIP_ADMIN)) {
			this.pip = new PIPManagement();
			this.tabSheet.addComponent(this.pip);
			this.tabSheet.getTab(this.pip).setCaption("PIP Management");
		} else {
			this.pip = null;
		}
		/*
		 * TODO  - figure out how to add this in
		 *
		if (((XacmlAdminUI)UI.getCurrent()).isAuthorized( 
				XacmlAdminAuthorization.AdminAction.ACTION_READ, 
				XacmlAdminAuthorization.AdminResource.RESOURCE_POLICY_WORKSPACE)) {
			this.user = new UserManagement();
			this.tabSheet.addComponent(this.user);
			this.tabSheet.getTab(this.user).setCaption("User Management");
		}
		*/
		
		if (logger.isDebugEnabled()) {
			logger.debug("Done creating tabs.");
		}
	}
	
	public void refreshAttributes() {
		this.attributeDictionary.refreshContainer();
	}
	public void refreshObadvice() {
		this.obadvice.refreshContainer();
	}

	public void refreshPIPConfiguration() {
		this.pip.refreshContainer();
	}

	public void refreshPDPGroups() {
		this.pdp.refreshContainer();
	}

	public TabSheet getTabSheet() { return tabSheet;}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		// tabSheet
		tabSheet = new TabSheet();
		tabSheet.setImmediate(false);
		tabSheet.setWidth("100.0%");
		tabSheet.setHeight("100.0%");
		mainLayout.addComponent(tabSheet);
		mainLayout.setExpandRatio(tabSheet, 1.0f);
		
		// labelCopyright
		labelCopyright = new Label();
		labelCopyright.setImmediate(false);
		labelCopyright.setWidth("-1px");
		labelCopyright.setHeight("40px");
		labelCopyright
				.setValue("<center>&copy; 2013-2014 AT&T Intellectual Property. All rights reserved.</center>");
		mainLayout.addComponent(labelCopyright);
		mainLayout.setComponentAlignment(labelCopyright, new Alignment(48));
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("100.0%");
		horizontalLayout_1.setHeight("40px");
		horizontalLayout_1.setMargin(false);
		
		// embedded_1
		embedded_1 = new Embedded();
		embedded_1.setImmediate(false);
		embedded_1.setWidth("30px");
		embedded_1.setHeight("30px");
		embedded_1.setSource(new ThemeResource("img/att.png"));
		embedded_1.setType(1);
		embedded_1.setMimeType("image/png");
		horizontalLayout_1.addComponent(embedded_1);
		horizontalLayout_1.setComponentAlignment(embedded_1, new Alignment(33));
		
		// caption
		caption = new Label();
		caption.setImmediate(false);
		caption.setWidth("-1px");
		caption.setHeight("-1px");
		caption.setValue("AT&T Policy Engine Admin Console");
		horizontalLayout_1.addComponent(caption);
		horizontalLayout_1.setExpandRatio(caption, 1.0f);
		horizontalLayout_1.setComponentAlignment(caption, new Alignment(33));
		
		// labelWelcome
		labelWelcome = new Label();
		labelWelcome.setImmediate(false);
		labelWelcome.setWidth("-1px");
		labelWelcome.setHeight("40px");
		labelWelcome.setValue("Label");
		horizontalLayout_1.addComponent(labelWelcome);
		horizontalLayout_1.setComponentAlignment(labelWelcome,
				new Alignment(34));
		
		return horizontalLayout_1;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		//
		// This needs to be implemented
		//
	}
}
