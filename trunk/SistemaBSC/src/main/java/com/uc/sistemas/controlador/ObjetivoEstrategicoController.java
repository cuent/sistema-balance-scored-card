package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.ObjetivoEstrategico;
import com.uc.sistemas.controlador.util.JsfUtil;
import com.uc.sistemas.controlador.util.JsfUtil.PersistAction;
import com.uc.sistemas.facade.ObjetivoEstrategicoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("objetivoEstrategicoController")
@SessionScoped
public class ObjetivoEstrategicoController implements Serializable {

    @EJB
    private com.uc.sistemas.facade.ObjetivoEstrategicoFacade ejbFacade;
    private List<ObjetivoEstrategico> items = null;
    private ObjetivoEstrategico selected;

    public ObjetivoEstrategicoController() {
    }

    public ObjetivoEstrategico getSelected() {
        return selected;
    }

    public void setSelected(ObjetivoEstrategico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ObjetivoEstrategicoFacade getFacade() {
        return ejbFacade;
    }

    public ObjetivoEstrategico prepareCreate() {
        selected = new ObjetivoEstrategico();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ObjetivoEstrategico> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ObjetivoEstrategico getObjetivoEstrategico(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ObjetivoEstrategico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ObjetivoEstrategico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ObjetivoEstrategico.class)
    public static class ObjetivoEstrategicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ObjetivoEstrategicoController controller = (ObjetivoEstrategicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "objetivoEstrategicoController");
            return controller.getObjetivoEstrategico(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ObjetivoEstrategico) {
                ObjetivoEstrategico o = (ObjetivoEstrategico) object;
                return getStringKey(o.getIdObjetivoEstrategico());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ObjetivoEstrategico.class.getName()});
                return null;
            }
        }

    }

}
