package util;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Cor;

@FacesConverter(value = "fooConverter")
public class ConverterMenu implements Converter {

    private int counter;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        for (UIComponent child : component.getChildren()) {
            if (child instanceof UISelectItems) {
                List<Cor> items = (List<Cor>) ((UISelectItems) child).getValue();
                System.out.println("value cabuloso: " + value);
                return items.get(Integer.parseInt(value)-1);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return Integer.toString(++counter);
    }

}
