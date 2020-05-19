package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.CodeAlert;
import com.growup.comptadecision.domain.enumeration.TypeAlert;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BusinessAlertDTO implements Serializable {

    TypeAlert type;

    CodeAlert code;

    Map<String, Object> params = new HashMap<>();

    public BusinessAlertDTO() {
    }

    public BusinessAlertDTO(TypeAlert type, CodeAlert code) {
        this.type = type;
        this.code = code;
    }

    public TypeAlert getType() {
        return type;
    }

    public void setType(TypeAlert type) {
        this.type = type;
    }

    public CodeAlert getCode() {
        return code;
    }

    public void setCode(CodeAlert code) {
        this.code = code;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void addParam(String code, Object value) {
        this.params.put(code, value);
    }

    @Override
    public String toString() {
        return "BusinessAlertDTO{" +
            "type=" + type +
            ", code=" + code +
            ", params=" + params +
            '}';
    }
}
