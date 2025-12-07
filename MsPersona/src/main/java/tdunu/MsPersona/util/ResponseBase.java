package tdunu.MsPersona.util;

import lombok.Data;

@Data
public class ResponseBase<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> ResponseBase<T> ok(T data) {
        ResponseBase<T> response = new ResponseBase<>();
        response.setCode(200);
        response.setMessage("Éxito");
        response.setData(data);
        return response;
    }

    public static <T> ResponseBase<T> error(String message) {
        ResponseBase<T> response = new ResponseBase<>();
        response.setCode(500);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
}