package com.ecommerce.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dguzik
 */
@Entity
@Table(name="orders")
public class Order extends EntityId {

    @Column(nullable=false, unique=true)
    private String orderNumber;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="order")
    private Set<OrderItem> items;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="cust_id")
    private Customer customer;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="delivery_addr_id")
    private Address deliveryAddress;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="billing_addr_id")
    private Address billingAddress;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="payment_id")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
    private Date createdOn;

    public Order()
    {
        this.items = new HashSet<OrderItem>();
        this.status = OrderStatus.NEW;
        this.createdOn = new Date();
    }

    public BigDecimal getTotalAmount()
    {
        BigDecimal amount = BigDecimal.ZERO;
        for (OrderItem item : items)
        {
            amount = amount.add(item.getSubTotal());
        }
        return amount;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
