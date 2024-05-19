package id.ac.ui.cs.advprog.heymartorder.model;

import java.util.HashMap;

public class KeranjangBelanjaBuilder {

    private Long id;
    private Long supermarketId;


    public KeranjangBelanjaBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public KeranjangBelanjaBuilder setSupermarketId(Long supermarketId) {
        this.supermarketId = supermarketId;
        return this;
    }

    public KeranjangBelanja build() {
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setId(this.id);
        keranjangBelanja.setSupermarketId(this.supermarketId);
        return keranjangBelanja;
    }

}
