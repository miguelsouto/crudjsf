package com.crudjsf.domain.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.crudjsf.domain.model.Login;
import com.crudjsf.domain.repository.LoginDao;

@ManagedBean(name = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Login login;
	private Login novoLogin = new Login();

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public Login getNovoLogin() {
		return novoLogin;
	}

	public void setNovoLogin(Login novoLogin) {
		this.novoLogin = novoLogin;
	}
	
	public void salvar(){
		LoginDao loginDao = new LoginDao();
		this.novoLogin.setSenha(convertStringToMd5(this.novoLogin.getSenha()));
		loginDao.salvar(novoLogin);
		loginDao = new LoginDao();
    	novoLogin = new Login();
    	RequestContext.getCurrentInstance().execute("PF('loginDialog').hide()");
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Novo login adicionado com sucesso!"));
	}
	
	public LoginMBean() {
		this.setLogin(new Login());
	}
	
	public String logar() {
		LoginDao loginDao = new LoginDao();
		Login sessionLogin = loginDao.findByLogin(login.getLogin());
		if(sessionLogin != null && sessionLogin.getLogin().equals(login.getLogin()) && sessionLogin.getSenha().equals(convertStringToMd5(login.getSenha()))) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("usuario", login);
			return "/app/associado?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info:", "Usuário e/ou senha incorretos!"));
		return "/login?faces-redirect=true";
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
	
	private String convertStringToMd5(String valor) {
	  MessageDigest mDigest;
	  try {
	      mDigest = MessageDigest.getInstance("MD5");
	      byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
	      StringBuffer sb = new StringBuffer();
	      for (byte b : valorMD5){
	             sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
	      }
	      return sb.toString();
	  } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	      return null;
	  } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	      return null;
	  }
	}
	
}
