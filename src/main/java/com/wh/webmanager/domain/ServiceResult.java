package com.wh.webmanager.domain;

public class ServiceResult {

    private Boolean result;
    private String msg;

    public ServiceResult() {
    }

    public ServiceResult(Boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public ServiceResult(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
