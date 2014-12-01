package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.context.FacesContext;

import bean.LoginBean;


public class BeanUtil {
	
	
	public static LoginBean getLoginBean() {
		FacesContext contextl = FacesContext.getCurrentInstance();
		
		LoginBean loginBean = contextl.getApplication().evaluateExpressionGet(
				contextl, "#{loginBean}", LoginBean.class);
		return loginBean;
	}
	
	public static String formataData(Date data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
		return sdf.format(data.getTime());
	}

}
