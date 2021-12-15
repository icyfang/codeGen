package com.headstrong.codegen.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * page model
 *
 * @author Hodur
 * @date 2020/1/8
 */
@ApiModel("Page Model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page extends Sort implements Serializable {

    private static final long serialVersionUID = 3019143308219160792L;

    @ApiModelProperty(value = "The number of page", required = true, example = "1")
    protected int pageNo;

    @ApiModelProperty(value = "The number of page size", required = true, example = "20")
    protected int pageSize;

}
