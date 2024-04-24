package id.ac.ui.cs.advprog.heymartorder.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashMap;

@Builder
@Getter
public class KeranjangBelanja {
    String supermarketId;
    HashMap<String, Integer> productMap;

    @Builder
    public KeranjangBelanja(String supermarketId, HashMap<String, Integer> productMap) {
        this.supermarketId = supermarketId;
        this.productMap = new HashMap<>();
    }

    public void setSupermarketId(String supermarketId) {
        if (supermarketId == null) {
            throw new IllegalArgumentException("");
        }
        this.supermarketId = supermarketId;
    }

    public void setProductMap(HashMap<String, Integer> productMap) {
        if (productMap != null) {
            for (HashMap.Entry<String, Integer> entry : productMap.entrySet()) {
                if (entry.getKey() == null) {
                    throw new IllegalArgumentException("");
                }
            }
        }
        this.productMap = productMap;
    }
}
