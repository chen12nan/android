package notes.xingkd.androidnotes.datastruct;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Created by xkd on 16-6-20.
 */
public class TestList {

    public static void testHashTable()
    {
        Hashtable<String, Object> hashtable = new Hashtable<>(100);
    }

    public static void testList()
    {
        ArrayList<Integer> lists = new ArrayList<Integer>(10);

        lists.add(3);
        lists.add(2);
        lists.add(8);
        lists.add(5);


        for(int i = 0; i<lists.size(); i++)
        {
            System.out.println("value = " + lists.get(i));
        }

        Vector<Integer> vector = new Vector<>(10);
        vector.add(100);
        System.out.println("value = " + vector.size());

        LinkedList<Integer> linkedList  = new LinkedList<>();
    }
}
