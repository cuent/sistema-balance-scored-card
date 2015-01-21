package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Responsable;
import com.uc.sistemas.controlador.util.JsfUtil;
import com.uc.sistemas.controlador.util.JsfUtil.PersistAction;
import com.uc.sistemas.facade.ResponsableFacade;

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

@Named("responsableController")
@SessionScoped
public class ResponsableController implements Serializable {

    @EJB
    private com.uc.sistemas.facade.ResponsableFacade ejbFacade;
    private List<Responsable> items = null;
    private Responsable selected;

    public ResponsableController() {
    }

    public Responsable getSelected() {
        return selected;
    }

    public void setSelected(Responsable selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getResponsablePK().setIdObjetivo(selected.getObjetivoEstrategico().getIdObjetivoEstrategico());
        selected.getResponsablePK().setIdUsuario(selected.getUsuario().getIdUsuario());
    }

    protected void initializeEmbeddableKey() {
        selected.setResponsablePK(new com.uc.sistemas.modelo.ResponsablePK());
    }

    private ResponsableFacade getFacade() {
        return ejbFacade;
    }

    public Responsable prepareCreate() {
        selected = new Responsable();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ResponsableCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ResponsableUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ResponsableDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Responsable> getItems() {
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

    public Responsable getResponsable(com.uc.sistemas.modelo.ResponsablePK id) {
        return getFacade().find(id);
    }

    public List<Responsable> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Responsable> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Responsable.class)
    public static class ResponsableControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ResponsableController controller = (ResponsableController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "responsableController");
            return controller.getResponsable(getKey(value));
        }

        com.uc.sistemas.modelo.ResponsablePK getKey(String value) {
            com.uc.sistemas.modelo.ResponsablePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.uc.sistemas.modelo.ResponsablePK();
            key.setIdResponsable(Integer.parseInt(values[0]));
            key.setIdObjetivo(Integer.parseInt(values[1]));
            key.setIdUsuario(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(com.uc.sistemas.modelo.ResponsablePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdResponsable());
            sb.append(SEPARATOR);
            sb.append(value.getIdObjetivo());
            sb.append(SEPARATOR);
            sb.append(value.getIdUsuario());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Responsable) {
                Responsable o = (Responsable) object;
                return getStringKey(o.getResponsablePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Responsable.class.getName()});
                return null;
            }
        }

    }

}
