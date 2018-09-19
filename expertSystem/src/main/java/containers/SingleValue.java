package containers;

import java.util.ArrayList;

public class SingleValue extends Value {

    public SingleValue(String parametr, boolean selectionType) {
        this.inputPattern = new ArrayList<String>();
        this.inputPattern.add(parametr);
        this.selectionType = selectionType;
    }
}
