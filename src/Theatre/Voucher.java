package Theatre;

import java.util.ArrayList;

/**
 * Class for holding information about Vouchers that are associated to user email addresses
 */
public class Voucher {

    /**
     * Email address of associated user
     */
    private String email;
    /**
     * Number of vouchers on the account
     */
    private int count;

    /**
     * Basic constructor for Voucher
     * @param count Number of vouchers
     * @param email User email address
     */
    public Voucher(int count, String email){
        this.count = count;
        this.email = email;
    }

    /**
     * Static method to find Vouchers associated with a passed email
     * @param vouchers ArrayList of Voucher
     * @param email Email to search for
     * @return Voucher object if found, null if not
     */
    public static Voucher findByEmail(ArrayList<Voucher> vouchers, String email){
        for(Voucher v: vouchers){
            if(v.getEmail().equals(email))
                return v;
        }
        return null;
    }

    /**
     * Basic getter for email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Basic getter for count
     * @return count
     */
    public int getCount() {
        return count;
    }
}
