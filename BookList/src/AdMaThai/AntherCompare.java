package AdMaThai;

import java.util.Comparator;

public class AntherCompare implements Comparator<Contact> {
    @Override
    public int compare(Contact o1, Contact o2) {

        return o1.getPhone().compareTo(o2.getPhone());
    }
}
