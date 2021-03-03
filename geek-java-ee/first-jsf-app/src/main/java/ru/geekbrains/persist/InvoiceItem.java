package ru.geekbrains.persist;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="invoice_items")
@NamedQueries({
        @NamedQuery(name = "findAllInvoiceItems", query = "select p from InvoiceItem p JOIN FETCH p.invoice"),
        @NamedQuery(name = "findInvoiceItemById", query = "from InvoiceItem c where c.id = :id"),
        @NamedQuery(name = "countAllInvoiceItem", query = "select count(*) from InvoiceItem "),
        @NamedQuery(name = "deleteByIdInvoiceItem", query = "delete from InvoiceItem c where c.id = :id")

})
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="invoice_id", nullable=false)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="product_id", nullable=false)
    private Product product;

    @Column
    private BigDecimal count;

    @Column
    private BigDecimal price;

    public InvoiceItem() {
    }

    public InvoiceItem(Long id, Invoice invoice, Product product, BigDecimal count, BigDecimal price) {
        this.id = id;
        this.invoice = invoice;
        this.product = product;
        this.count = count;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setCategory(Invoice invoice) {
        this.invoice = invoice;
    }



    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object other) {
        return (other instanceof InvoiceItem) && (id != null)
                ? id.equals(((InvoiceItem) other).id)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("InvoiceItem[%d, %s]", id);
    }

}
