import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

/**
 * <h1>UIListCellRenderer</h1>
 * Customized {@link javax.swing.ListCellRenderer} for {@link JList} component.*/
public class UIListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Order order = (Order) value;
        String name = order.getOwner().getLastName();
        int age = order.getOwner().getAge();
        String address = order.getOwner().getAddress();
        String labelText = "<html>Nom: " + name + "<br/>Age: " + age + "<br/Adresse : " + address;
        setText(labelText);

        return this;
    }

}
