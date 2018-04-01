package com.epam.lab.xml;

import com.epam.lab.xml.knife.Type;

import java.util.Comparator;

public class KnifeComparator implements Comparator<Type> {

    @Override
    public int compare(Type type, Type t1) {
        String knifeOrigin1 = type.getOrigin();
        String knifeOrigin2 = t1.getOrigin();
        return knifeOrigin1.compareToIgnoreCase(knifeOrigin2);
    }

}
