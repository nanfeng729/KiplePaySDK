package com.kiple.api.payment;

public enum PostTypeEnums {

    POST_APPLICATION_JSON("application/json"),
    POST_MULTIPART_FORM_DATA("multipart/form-data"),
    POST_FORM_DATA("application/x-www-form-urlencoded");

    private String type;

    private PostTypeEnums(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
