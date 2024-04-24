package id.ac.ui.cs.advprog.heymartorder.model;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Builder
@Getter
public class KeranjangBelanja {
    String supermarketId;
    HashMap<String, Integer> productMap;
}
