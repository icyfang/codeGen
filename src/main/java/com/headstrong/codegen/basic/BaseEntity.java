package com.headstrong.codegen.basic;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author Hodur
 * @date 2021/12/10
 */
@MappedSuperclass
@Data
@Accessors(chain = true)
public class BaseEntity {

    // country code, such as US/CA/MX/BR
    @Column(name = "country_code")
    private String countryCode;

    // creator ID
    @Column(name = "entry_id")
    private Integer entryId;

    // creation time
    @Column(name = "entry_datetime")
    private Date entryDatetime;

    // update the ID
    @Column(name = "update_id")
    private Integer updateId;

    // update time
    @Column(name = "update_datetime")
    private Date updateDatetime;

    // delete the ID
    @Column(name = "delete_id")
    private Integer deleteId;

    // delete the time
    @Column(name = "delete_datetime")
    private Date deleteDatetime;

    // version history
    @Column(name = "h_version")
    private Integer hVersion;
}
