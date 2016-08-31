package Web;

/**
 * Created by NHOBB65 on 31/08/2016.
 */
//import org.primefaces.context.RequestContext;
//import org.primefaces.event.SelectEvent;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

//import org.primefaces.context.RequestContext;
//import org.primefaces.event.SelectEvent;


@ManagedBean
public class SelectOneMenueView {
    private String car;
    private List<SelectItem> cars;

    @PostConstruct
    public void init() {
        //cars
        SelectItemGroup g1 = new SelectItemGroup("Categories");
        g1.setSelectItems(new SelectItem[] {new SelectItem("basic", "basic"), new SelectItem("premium", "premium"), new SelectItem("buisness", "buisness")});
        cars = new ArrayList<SelectItem>();
        cars.add(g1);

    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public List<SelectItem> getCars() {
        return cars;
    }
}