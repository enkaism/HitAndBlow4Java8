import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by enkaism on 12/22/15.
 * 要素数4までのArrayList
 */
public class HABArrayList<T> extends ArrayList<T> {

    public HABArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public HABArrayList() {
    }

    public HABArrayList(Collection<? extends T> c) {
        super(c);
    }

    @Override
    public boolean add(T t) {
        return is() && super.add(t);
    }

    @Override
    public void add(int index, T element) {
        if (is()) return;
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return is() && super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return is() && super.addAll(index, c);
    }

    private boolean is() {
        boolean is = this.size() < Const.NUMBER_OF_DIGITS;
        System.out.println(this.size());
        if (!is) System.out.println("要素が" + Const.NUMBER_OF_DIGITS + "個を超えています");
        return is;
    }
}
