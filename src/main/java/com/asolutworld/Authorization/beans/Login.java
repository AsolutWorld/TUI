package main.java.com.asolutworld.Authorization.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import main.java.com.asolutworld.Authorization.dao.LoginDAO;
import main.java.com.asolutworld.Constants.Strings;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;
	private String valid;
	private int u_id;
    private boolean isAdmin=false;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    //validate login

    public String validateUsernamePassword() {
		valid = LoginDAO.validate(user, pwd);
		if (valid.equals(Strings.ADMIN))isAdmin=true;
        else isAdmin=false;
		if (valid.equals(Strings.ADMIN)||valid.equals(Strings.USER)) {
			HttpSession session = SessionBean.getSession();
			session.setAttribute("u_id",LoginDAO.getU_ID());
            u_id=LoginDAO.getU_ID();

            session.setAttribute("username", user);
            session.setAttribute("role",valid);
			return "/faces/main";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "index";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionBean.getSession();
		session.invalidate();
		return "index";
	}

	public boolean getIsLogin(){
		HttpSession session = SessionBean.getSession();
		if(session.getAttribute("u_id")==null)return false;
		else return true;
	}

    public boolean getIsAdmin(){
        return isAdmin;
    }
}
