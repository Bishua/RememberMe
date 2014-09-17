package com.bishua.rememberme2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BishUA on 17.09.2014.
 */
public class LessonList implements Serializable {
    private List<Lesson> list = new ArrayList<Lesson>();

    public LessonList() {
    }

    public LessonList(List<Lesson> list){
        this.list = list;
    }

    public void putValue(Lesson lesson){
        list.add(lesson);
    }

    public List<Lesson> getList() {
        return list;
    }

    public void setList(List<Lesson> list) {
        this.list = list;
    }
}
