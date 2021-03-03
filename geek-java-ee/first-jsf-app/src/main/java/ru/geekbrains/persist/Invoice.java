package ru.geekbrains.persist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="invoice")
@NamedQueries({
        @NamedQuery(name = "findAllInvoice", query = "from Invoice"),
        @NamedQuery(name = "findInvoiceById", query = "from Invoice c  where c.id = :id"),
        @NamedQuery(name = "countAllInvoice", query = "select count(*) from Invoice "),
        @NamedQuery(name = "deleteByIdInvoice", query = "delete from Invoice c where c.id = :id")

})
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 1024)
    private String description;

    @OneToMany(mappedBy = "invoice", orphanRemoval = true)
    private List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();

    public Invoice() {
    }


    public Invoice(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Invoice) && (id != null)
                ? id.equals(((Invoice) other).id)
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
        return String.format("Invoice[%d, %s]", id, name);
    }

}
