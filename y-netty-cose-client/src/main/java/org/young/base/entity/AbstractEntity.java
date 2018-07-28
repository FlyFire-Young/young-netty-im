package org.young.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import java.util.Date;


/**
 * Created by Young on 2017/9/2.
 */
@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> {

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @CreatedDate
    private Date createdDate;


    /**
     * 最后修改时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @LastModifiedDate
    private Date lastModifiedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
