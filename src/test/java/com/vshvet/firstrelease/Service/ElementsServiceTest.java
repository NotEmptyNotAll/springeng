package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Service.ElementsService;
import com.vshvet.firstrelease.payload.Request.SaveOrUpdateElementsRequest;
import com.vshvet.firstrelease.payload.Response.ElementsResponse;
import com.vshvet.firstrelease.payload.Response.TreeToColumnsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class ElementsServiceTest {

    @Autowired
    private ElementsService elementsService;

    @Test
    void save() {
        List<SaveOrUpdateElementsRequest> list = new ArrayList<>();
        list.add(new SaveOrUpdateElementsRequest(4, 5, 1));
        list.add(new SaveOrUpdateElementsRequest(1, 2, 0));
        list.add(new SaveOrUpdateElementsRequest(2, 3, 0));
        list.add(new SaveOrUpdateElementsRequest(3, 4, 0));
        elementsService.save(list);
    }

    @Test
    void getAllRootElemByAutoId() {
        Integer id = 1;
        List<ElementsResponse> list = elementsService.getAllRootElemByAutoId(id);
        System.out.println(list.toString());
    }

    @Test
    void saveName() {
        Integer id = 1;
        List<ElementsResponse> list = elementsService.getAllRootElemByAutoId(id);
        System.out.println(list.toString());
    }

    @Test
    void getTableColumn() {
        List<TreeToColumnsResponse> list = elementsService.getTableColumn();
        System.out.println(list);
    }


}
