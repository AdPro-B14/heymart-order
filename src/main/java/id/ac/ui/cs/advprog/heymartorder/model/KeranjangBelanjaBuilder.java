package id.ac.ui.cs.advprog.heymartorder.model;

import java.util.HashMap;

public class KeranjangBelanjaBuilder {

    private Long id;
    private Long supermarketId;
    private HashMap<String, Integer> productMap;

    public KeranjangBelanjaBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public KeranjangBelanjaBuilder setSupermarketId(Long supermarketId) {
        if (supermarketId == null)
            throw new IllegalArgumentException();
        this.supermarketId = supermarketId;
        return this;
    }

    public KeranjangBelanjaBuilder setProductMap(HashMap<String, Integer> productMap) {
        this.productMap = productMap;
        return this;
    }

    public KeranjangBelanja build() {
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setId(this.id);
        keranjangBelanja.setSupermarketId(this.supermarketId);
        keranjangBelanja.setProductMap(this.productMap);
        return keranjangBelanja;
    }

}
