package com.crudjsf.domain.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.crudjsf.domain.model.Associado;
import com.crudjsf.domain.repository.AssociadoDao;

@FacesConverter("associadoConverter")
public class AssociadoConverter implements Converter {

	AssociadoDao associadoDao = new AssociadoDao();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Associado associado = associadoDao.getById(Long.parseLong(value));
		return associado;
	}

	@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
		Associado associado = (Associado) value;
		return String.valueOf(associado.getId());
	}

}
