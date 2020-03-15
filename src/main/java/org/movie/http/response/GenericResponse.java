package org.movie.http.response;

public class GenericResponse<T> {

	private T data;
	private Integer code;

	public GenericResponse(T data, Integer code) {
		this.data = data;
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
