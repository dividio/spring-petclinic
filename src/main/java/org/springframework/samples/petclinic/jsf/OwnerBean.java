package org.springframework.samples.petclinic.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.springframework.samples.petclinic.jsf.util.JsfUtil;
import org.springframework.samples.petclinic.jsf.util.PaginationHelper;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;

@ManagedBean(name = "ownerBean")
@RequestScoped
public class OwnerBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6492426175462095648L;
	
	private Owner current;
    private DataModel items = null;
    
    @ManagedProperty(value = "#{clinicServiceImpl}")
    private ClinicService clinicService;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ClinicService getClinicService() {
		return clinicService;
	}

	public void setClinicService(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	public OwnerBean() {
    }

    public Owner getSelected() {
        if (current == null) {
            current = new Owner();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ClinicService getFacade() {
        return clinicService;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().findOwnerByLastName("%").size();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(new ArrayList(getFacade().findOwnerByLastName("%")));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Owner) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Owner();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().saveOwner(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("messages.messages").getString("OwnerCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("messages.messages").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Owner) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().saveOwner(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("messages.messages").getString("OwnerUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("messages.messages").getString("PersistenceErrorOccured"));
            return null;
        }
    }

//    public String destroy() {
//        current = (Owner) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        performDestroy();
//        recreatePagination();
//        recreateModel();
//        return "List";
//    }
//
//    public String destroyAndView() {
//        performDestroy();
//        recreateModel();
//        updateCurrentItem();
//        if (selectedItemIndex >= 0) {
//            return "View";
//        } else {
//            // all items were removed - go back to list
//            recreateModel();
//            return "List";
//        }
//    }

//    private void performDestroy() {
//        try {
//            getFacade().(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OwnerDeleted"));
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//        }
//    }

    private void updateCurrentItem() {
        int count = getFacade().findOwnerByLastName("%").size();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = (new ArrayList<Owner>(getFacade().findOwnerByLastName("%"))).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(new ArrayList<Owner>(clinicService.findOwnerByLastName("%")), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(new ArrayList<Owner>(clinicService.findOwnerByLastName("%")), true);
    }

    public Owner getOwner(java.lang.Integer id) {
        return clinicService.findOwnerById(id);
    }

    @FacesConverter(forClass = Owner.class)
    public static class OwnerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OwnerBean controller = (OwnerBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ownerBean");
            return controller.getOwner(getKey(value));
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
            if (object instanceof Owner) {
                Owner o = (Owner) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Owner.class.getName());
            }
        }

    }

}
