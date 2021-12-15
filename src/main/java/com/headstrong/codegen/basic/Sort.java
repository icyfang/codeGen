package com.headstrong.codegen.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description
 *
 * @author Hodur
 * @date 2020/1/8
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sort implements Serializable {

    private static final long serialVersionUID = 8070813909443248218L;

    @ApiModelProperty(value = "sort field", example = "id")
    protected String sort;

    /**
     * asc, desc
     */
    @ApiModelProperty(value = "sort direction", example = "asc")
    protected String direction;
}
