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
public class SimpleFormBean implements Serializable {

    private List<String> languages = Arrays.asList("Java", "Ceylon", "Scala", "Groovy");
    private String username;
    private List<String> usedLanguages;
    private String usedLanguagesString=null;


    public void processForm(){

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Form successfully processed", "Form successfully processed" ));

        usedLanguagesString = String.format("You use the following languages : %s", Joiner.on(", ").join(usedLanguages));

    }



    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsedLanguages(List<String> usedLanguages) {
        this.usedLanguages = usedLanguages;
    }

    public List<String> getUsedLanguages() {
        return usedLanguages;
    }

    public void setUsedLanguagesString(String usedLanguagesString) {
        this.usedLanguagesString = usedLanguagesString;
    }

    public String getUsedLanguagesString() {
        return usedLanguagesString;
    }
}
