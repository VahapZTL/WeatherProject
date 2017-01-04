package com.vahapoztel.weatherproject.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private RegisterData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public RegisterData getData() {
        return data;
    }

    public void setData(RegisterData data) {
        this.data = data;
    }

}
