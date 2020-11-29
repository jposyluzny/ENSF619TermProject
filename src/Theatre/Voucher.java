package Theatre;

import java.util.ArrayList;

public class Voucher {

    private String email;
    private int count;

    public Voucher(int count, String email){
        this.count = count;
        this.email = email;
    }

    public static Voucher findByEmail(ArrayList<Voucher> vouchers, String email){
        for(Voucher v: vouchers){
            if(v.getEmail().equals(email))
                return v;
        }
        return null;
    }

    public String getEmail() {
        return email;
    }

    public int getCount() {
        return count;
    }
}
