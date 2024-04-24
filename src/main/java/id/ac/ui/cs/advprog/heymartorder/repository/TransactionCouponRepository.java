package id.ac.ui.cs.advprog.heymartorder.repository;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionCouponRepository {
    private List<TransactionCoupon> tcCoupons = new ArrayList<>();

    public TransactionCoupon save(TransactionCoupon tcCoupon) {
        return null;
    }

    public TransactionCoupon findById(String id) {
        return null;
    }

    public void delete(TransactionCoupon tcCoupon) {
    }

}
