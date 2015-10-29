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

	//validate login
	public String validateUsernamePassword() {
		String valid = LoginDAO.validate(user, pwd);
		if (valid.equals(Strings.ADMIN)||valid.equals(Strings.USER)) {
			HttpSession session = SessionBean.getSession();
			session.setAttribute("u_id",LoginDAO.getU_ID());
            session.setAttribute("username", user);
            session.setAttribute("role",valid);
			return "database";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "home";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionBean.getSession();
		session.invalidate();
		return "home";
	}
}
