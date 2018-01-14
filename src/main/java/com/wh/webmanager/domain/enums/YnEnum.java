package com.wh.webmanager.domain.enums;

public enum YnEnum {

    Y((byte)1, "有效"),
    N((byte)0, "无效");

    private byte value;
    private String content;

    YnEnum(byte value, String content) {
        this.value = value;
        this.content = content;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "YnEnum{" +
                "value=" + value +
                ", content='" + content + '\'' +
                '}';
    }
}
