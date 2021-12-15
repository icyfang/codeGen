package com.headstrong.codegen.basic;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Hodur
 * @date 2021/11/30
 */
@Data
public class PageRequestVO extends Page {

    @Valid
    private List<QueryItemVO> queryItems;
}
