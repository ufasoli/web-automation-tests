package web.automation.tests;

import com.google.common.base.Joiner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 26.09.13
 * Time: 10:46
 */

@ViewScoped
@ManagedBean
public class SimpleFormBean2 implements Serializable {

    private List<String> frameworks = Arrays.asList("JSF", "Spring", "Hibernate", "Grails", "Play!");
    private String username;
    private List<String> usedFrameworks;
    private String usedFrameworksString =null;


    public void processForm(){

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Form successfully processed", "Form successfully processed" ));


        usedFrameworksString = String.format("You use the following frameworks : %s", Joiner.on(", ").join(usedFrameworks));

    }



    public List<String> getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(List<String> frameworks) {
        this.frameworks = frameworks;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsedFrameworks(List<String> usedFrameworks) {
        this.usedFrameworks = usedFrameworks;
    }

    public List<String> getUsedFrameworks() {
        return usedFrameworks;
    }

    public void setUsedFrameworksString(String usedFrameworksString) {
        this.usedFrameworksString = usedFrameworksString;
    }

    public String getUsedFrameworksString() {
        return usedFrameworksString;
    }
}
