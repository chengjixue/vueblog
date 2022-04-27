package edu.xcj.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/4/27 17:07
 */
@Data
public class Result implements Serializable {
    private  int code;//返回码
    private  String msg;
    private  Object data;

    public static Result succ(Object data){
        return succ(200,"成功",data);
    }
    public static Result succ(int code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result fail(int code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result fail(String msg,Object data){
        return fail(400,msg,data);
    }
    public static Result fail(String msg){
        return fail(msg,null);
    }
}
