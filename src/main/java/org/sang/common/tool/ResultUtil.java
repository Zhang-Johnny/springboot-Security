package org.sang.common.tool;

import org.sang.bean.Result;
import org.sang.enums.ResultEnum;

/**
 * Created by zhangjun
 * 2017-01-21 13:39
 */
public class ResultUtil {

    public static Result success() {
        return success(null);
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }
    public static Result success(Object object1,Object object2) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object1);
        result.setData(object2);
        return result;
    }
    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}
