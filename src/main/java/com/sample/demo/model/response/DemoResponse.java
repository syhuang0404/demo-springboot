package com.sample.demo.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "API Response", description = "General Response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemoResponse {
	@Schema(name = "message", description = "message")
	private String message;
	@Schema(name = "return data", description = "return data")
    private List<Object> rows;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }

    public List<Object> getRows() {
        return rows;
    }
}
