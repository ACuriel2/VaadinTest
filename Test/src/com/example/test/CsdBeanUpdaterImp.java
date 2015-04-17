package com.example.test;

import com.vaadin.data.util.BeanItemContainer;

public class CsdBeanUpdaterImp implements CsdBeanUpdater {

	private BeanItemContainer<CsdBean> beanContainer;

	public CsdBeanUpdaterImp(final BeanItemContainer<CsdBean> beanContainer) {
		this.beanContainer = beanContainer;
	}

	@Override
	public void updateTable(final CsdBean bean) {
		beanContainer.addBean(bean);
	}

}
