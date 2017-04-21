package com.ecommerce.shop.entity;

import javax.persistence.Column;
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
}
