package com.ecommerce.core.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by dguzik
 */
@Entity
@Table(name="order_items")
public class OrderItem extends EntityId {

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private BigDecimal price;

    private int quantity;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    public BigDecimal getSubTotal()
    {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

}
