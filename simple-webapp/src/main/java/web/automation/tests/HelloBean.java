
package web.automation.tests;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class HelloBean implements Serializable {

    private String name;
    private String msg;
    private String title = "Selenium tests simple jsf page";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void printMessage(){
        if(name != null && name.length() > 1){
            msg =  String.format("Hello %s !!!", name);
        }
        else{
            msg = null;
        }

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}