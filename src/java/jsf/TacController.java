package jsf;

import jpa.entities.Tac;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.TacFacade;

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

@Named("tacController")
@SessionScoped
public class TacController implements Serializable {

    @EJB
    private jpa.session.TacFacade ejbFacade;
    private List<Tac> items = null;
    private Tac selected;

    public TacController() {
    }

    public Tac getSelected() {
        return selected;
    }

    public void setSelected(Tac selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getTacPK().setIdCoreteam(selected.getCoreTeam().getIdCoreteam());
        selected.getTacPK().setIdIssue(selected.getIssue().getIdIssue());
    }

    protected void initializeEmbeddableKey() {
        selected.setTacPK(new jpa.entities.TacPK());
    }

    private TacFacade getFacade() {
        return ejbFacade;
    }

    public Tac prepareCreate() {
        selected = new Tac();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("TacCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("TacUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("TacDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tac> getItems() {
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

    public Tac getTac(jpa.entities.TacPK id) {
        return getFacade().find(id);
    }

    public List<Tac> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tac> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tac.class)
    public static class TacControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TacController controller = (TacController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tacController");
            return controller.getTac(getKey(value));
        }

        jpa.entities.TacPK getKey(String value) {
            jpa.entities.TacPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.TacPK();
            key.setIdTAC(Integer.parseInt(values[0]));
            key.setTACName(values[1]);
            key.setIdIssue(Integer.parseInt(values[2]));
            key.setIdCoreteam(Integer.parseInt(values[3]));
            key.setIdPM(Integer.parseInt(values[4]));
            return key;
        }

        String getStringKey(jpa.entities.TacPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdTAC());
            sb.append(SEPARATOR);
            sb.append(value.getTACName());
            sb.append(SEPARATOR);
            sb.append(value.getIdIssue());
            sb.append(SEPARATOR);
            sb.append(value.getIdCoreteam());
            sb.append(SEPARATOR);
            sb.append(value.getIdPM());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Tac) {
                Tac o = (Tac) object;
                return getStringKey(o.getTacPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tac.class.getName()});
                return null;
            }
        }

    }

}
