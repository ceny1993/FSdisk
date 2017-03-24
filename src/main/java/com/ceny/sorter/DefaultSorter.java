package com.ceny.sorter;

import com.ceny.app.UserFile;

import java.util.Comparator;

/**
 * Created by chensongkui on 2017/3/24.
 */
public class DefaultSorter implements Comparator<UserFile> {
    @Override
    public int compare(UserFile o1, UserFile o2) {
        return o1.getFileName().compareTo(o2.getFileName());
    }
}
