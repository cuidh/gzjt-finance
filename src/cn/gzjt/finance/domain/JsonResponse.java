package cn.gzjt.finance.domain;

import cn.gzjt.finance.utils.TextUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通用的json响应体
 *
 * @param <T> data域类型
 * @create jianwei.zhou
 */
public class JsonResponse<T> {

    private static final int ok_common = 200;
    private static final String ok_msg_common = "ok";
    private static final int error_common = 550;
    private static final int error_parameter = 551;
    private static final String error_msg_common = "common error!";
    private static final String error_msg_parameter = "invalid parameter!";

    //元数据
    private Meta meta;
    //数据
    private T data;

    public static void write(String ret, HttpServletResponse resp) throws IOException {
        new ObjectMapper().writeValue(resp.getWriter(), obtain(ret));
    }

    public static JsonResponse obtain(String ret) {
        JsonResponse<String> jsonResponse;
        if (TextUtils.isEmpty(ret)) {
            jsonResponse = JsonResponse.obtainOk();
        } else {
            jsonResponse = JsonResponse.obtainError(ret);
        }
        return jsonResponse;
    }

    public static <T> JsonResponse obtainOk() {
        return obtainOk(null);
    }

    public static <T> JsonResponse obtainOk(T data) {
        JsonResponse response = new JsonResponse();
        response.meta = new Meta(ok_common, ok_msg_common);
        response.data = data;
        return response;
    }

    public static JsonResponse obtainParameterError() {
        return obtainError(error_parameter, error_msg_parameter);
    }

    public static JsonResponse obtainError() {
        return obtainError(error_common, error_msg_common);
    }

    public static JsonResponse obtainError(String msg) {
        JsonResponse response = new JsonResponse();
        response.meta = new Meta(error_common, msg);
        return response;
    }

    public static JsonResponse obtainError(int code, String msg) {
        JsonResponse response = new JsonResponse();
        response.meta = new Meta(code, msg);
        return response;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    static class Meta {
        private int code;
        private String msg;

        public Meta(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
