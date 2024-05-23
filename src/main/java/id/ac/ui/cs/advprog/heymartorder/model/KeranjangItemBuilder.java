package id.ac.ui.cs.advprog.heymartorder.model;

public class KeranjangItemBuilder {

    private String productId;
    private Integer amount;

    public KeranjangItemBuilder setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public KeranjangItemBuilder setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public KeranjangItem build() {
        KeranjangItem keranjangItem = new KeranjangItem();
        keranjangItem.setProductId(this.productId);
        keranjangItem.setAmount(this.amount);
        return keranjangItem;
    }

}
