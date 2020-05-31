package com.ee.santosh.pojo.classes;

import java.util.List;

public class TestData {
    public String title;
    public List<DataItem> data;

    public String getTitle() {
        return title;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }
}
