package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TreeToColumnsResponse {

    private String name;


    private List<ColumnResponse> columnResponseList;

    public TreeToColumnsResponse() {
    }

    public TreeToColumnsResponse(String name, List<ColumnResponse> columnResponseList) {
        this.name = name;
        this.columnResponseList = columnResponseList;
    }

    public TreeToColumnsResponse(Elements elements) {
        this.name = elements.getParameterNamesByParamNameFk().getName();
        this.columnResponseList = new ArrayList<>();
        elements.getChildElements().forEach(elem -> {
            if (elem.getDate() == null) {
                this.columnResponseList.add(new ColumnResponse(elem));
            }
            Collections.sort(columnResponseList, Comparator.comparingInt(ColumnResponse::getSortNumber));
        });
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ColumnResponse> getColumnResponseList() {
        return columnResponseList;
    }

    public void setColumnResponseList(List<ColumnResponse> columnResponseList) {
        this.columnResponseList = columnResponseList;
    }
}
