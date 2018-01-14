package com.wh.webmanager.controller;

import com.alibaba.fastjson.JSON;
import com.wh.webmanager.domain.QueryParams;
import com.wh.webmanager.domain.ServiceResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {

    public String toResult(ServiceResult serviceResult){
        if(serviceResult!=null){
            return JSON.toJSONString(serviceResult);
        }
        return "";
    }

    protected Map<String, Object> toDataTable(List<?> data, QueryParams queryParams) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("draw", queryParams.getDraw());
        result.put("start", queryParams.getStartIndex());
        result.put("length", queryParams.getPageSize());
        result.put("recordsTotal", queryParams.getRecordsTotal());
        result.put("recordsFiltered", queryParams.getRecordsTotal());
        result.put("data", data);

        return result;
    }
}
