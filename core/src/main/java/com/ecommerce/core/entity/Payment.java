package com.ecommerce.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by dguzik
 */
@Entity
@Table(name="payments")
public class Payment extends EntityId {

    private String ccNumber;

    private String cvv;

    private BigDecimal amount;

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getCcNumber()
    {
        return ccNumber;
    }
    public void setCcNumber(String ccNumber)
    {
        this.ccNumber = ccNumber;
    }
    public String getCvv()
    {
        return cvv;
    }
    public void setCvv(String cvv)
    {
        this.cvv = cvv;
    }
    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
}
