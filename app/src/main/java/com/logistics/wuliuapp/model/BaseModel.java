package com.logistics.wuliuapp.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-11-28
 *
 * @desc
 */

public class BaseModel implements Serializable {
    String statusCode;
    String status;
    String message;
    //String data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "BaseModel{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
