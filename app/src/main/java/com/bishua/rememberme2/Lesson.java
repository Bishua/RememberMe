package com.bishua.rememberme2;

import java.io.Serializable;

/**
 * Created by BishUA on 17.09.2014.
 */
public class Lesson implements Serializable {
    private int id;
    private String enText;
    private String ruText;

    public Lesson(int id, String enText, String ruText) {
        this.id = id;
        this.enText = enText;
        this.ruText = ruText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnText() {
        return enText;
    }

    public void setEnText(String enText) {
        this.enText = enText;
    }

    public String getRuText() {
        return ruText;
    }

    public void setRuText(String ruText) {
        this.ruText = ruText;
    }
}
