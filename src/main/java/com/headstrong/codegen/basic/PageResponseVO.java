package com.headstrong.codegen.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * page vo
 *
 * @author Hodur
 * @date 2019/11/7
 */
@ApiModel
@Data
public class PageResponseVO<T> extends Page {
    @ApiModelProperty(value = "total", required = true, example = "0")
    private Integer total = 0;
    @ApiModelProperty(value = "page data", required = true, example = "{}")
    private List<T> records = new ArrayList<>();
}
