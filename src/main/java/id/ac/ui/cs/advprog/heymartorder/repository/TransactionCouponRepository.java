package id.ac.ui.cs.advprog.heymartorder.repository;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class TransactionCouponRepository {
    private List<TransactionCoupon> tcCoupons = new ArrayList<>();

    public TransactionCoupon save(TransactionCoupon tcCoupon) {
        for (TransactionCoupon t : tcCoupons) {
            if (t.getCouponId().equals(tcCoupon.getCouponId())) {
                throw new IllegalArgumentException();
            }
        }
        tcCoupons.add(tcCoupon);
        return tcCoupon;
    }

    public TransactionCoupon findById(String id) {
        for (TransactionCoupon t : tcCoupons) {
            if (t.getCouponId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public List<TransactionCoupon> findAll() {
        List<TransactionCoupon> result = new ArrayList<>();
        for (TransactionCoupon saved : tcCoupons) {
            result.add(saved);
        }
        return result;
    }

    public void delete(TransactionCoupon tcCoupon) {
        tcCoupons.remove(tcCoupon);
    }

}
