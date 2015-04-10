package jsf;

import jpa.entities.Pwiki;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.PwikiFacade;

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

@Named("pwikiController")
@SessionScoped
public class PwikiController implements Serializable {

    @EJB
    private jpa.session.PwikiFacade ejbFacade;
    private List<Pwiki> items = null;
    private Pwiki selected;

    public PwikiController() {
    }

    public Pwiki getSelected() {
        return selected;
    }

    public void setSelected(Pwiki selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setPwikiPK(new jpa.entities.PwikiPK());
    }

    private PwikiFacade getFacade() {
        return ejbFacade;
    }

    public Pwiki prepareCreate() {
        selected = new Pwiki();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("PwikiCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("PwikiUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("PwikiDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Pwiki> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Pwiki getPwiki(jpa.entities.PwikiPK id) {
        return getFacade().find(id);
    }

    public List<Pwiki> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pwiki> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Pwiki.class)
    public static class PwikiControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PwikiController controller = (PwikiController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pwikiController");
            return controller.getPwiki(getKey(value));
        }

        jpa.entities.PwikiPK getKey(String value) {
            jpa.entities.PwikiPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.PwikiPK();
            key.setIdPWiki(Integer.parseInt(values[0]));
            key.setIdTAC(values[1]);
            return key;
        }

        String getStringKey(jpa.entities.PwikiPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPWiki());
            sb.append(SEPARATOR);
            sb.append(value.getIdTAC());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pwiki) {
                Pwiki o = (Pwiki) object;
                return getStringKey(o.getPwikiPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pwiki.class.getName()});
                return null;
            }
        }

    }

}
