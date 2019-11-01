package VO;

import lombok.Data;

/**
 * Created by Hangqi Yu
 * 2019-10-10 18:02
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
