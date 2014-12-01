package util;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
public class PropertiesLoader {    
 
    //Crio uma instancia da classe properties
    private static Properties prop = new Properties();
     
    //Crio um método estático que pode ser acessado por outras classes da aplicação sem a necessidade de instanciar
    public static Properties propertiesLoader() {
    	
    	if(prop.isEmpty()){
    		//Informo o caminho onde se encontra meu arquivo properties de forma dinâmica
    		FacesContext context = FacesContext.getCurrentInstance();
    		ServletContext sc = (ServletContext) context.getExternalContext().getContext();
    		String atualDir = "";

//    		if(sc.getRealPath("/resources/config/").contains("\\wtpwebapps\\")) 
    			atualDir = sc.getRealPath("/resources/config/configuracaoLocal.properties");
//    		else 
//    			atualDir = sc.getRealPath("/resources/config/configuracaoInternet.properties");
    		//    	atualDir = sc.getRealPath("/resources/config/configuracao.properties");

    		try {   //Tento recuperar as informações do arquivo de propriedades        

    			//Apenas para testar imprimo o diretório atual do meu arquivo properties
    			System.out.println("caminho sevidor com atualDir: "+atualDir);
    			System.out.println("caminho sevidor com getRealPath: "+sc.getRealPath("/resources/config/"));
    			//            System.out.println("se o caminho sevidor contem wtpwebapps: "+sc.getRealPath("/resources/config/").contains("wtpwebapps"));


    			//Crio uma instância de File passando o meu arquivo .properties via construtor
    			File file = new File(atualDir);  
    			//Agora crio uma instância de FileInputStream passando via construtor o objeto file instanciado acima
    			FileInputStream fileInputStream = new FileInputStream(file);
    			//Leio o fileInputStream recuperando assim o mapa contendo chaves e valores
    			prop.load(fileInputStream);
    			//Fecho o fileInputStream
    			fileInputStream.close();           
    		} catch (Exception e) {
    			//Trato possíveis exceções
    		}
    	}
    	return prop;
        //Retorno um objeto prop com o mapa correspondente ao meu arquivo properties
    }
     
}