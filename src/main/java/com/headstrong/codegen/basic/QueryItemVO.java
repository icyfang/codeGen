package com.headstrong.codegen.basic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Hodur
 * @date 2021/11/30
 */
@Data
public class QueryItemVO {

    @ApiModelProperty(example = "countryCode")
    @NotEmpty
    private String queryItem;

    @NotEmpty
    @ApiModelProperty(example = "GL")
    private String value;
}
