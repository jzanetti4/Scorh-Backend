package com.order.util;

import com.order.vo.ResultVO;

public class ResultUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("0");
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }


    public static ResultVO falure(String defaultMessage) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("1");
        resultVO.setMsg(defaultMessage);
        return resultVO;

    }
}
