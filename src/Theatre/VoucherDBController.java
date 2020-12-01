package Theatre;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB Controller for getting information about Voucher objects from database
 */
public class VoucherDBController extends DBController{

    /**
     * Single instance of VoucherDBController
     */
    static VoucherDBController singleInstance = null;

    /**
     * Method for getting single instance of VoucherDBController
     * @return VoucherDBController instance
     */
    public static VoucherDBController getSingleInstance() {
        if(singleInstance == null){
            singleInstance = new VoucherDBController();
        }
        return singleInstance;
    }

    /**
     * Method to get all Vouchers from database
     * @return ResultSet containing all vouchers
     */
    public ResultSet getVouchers(){
        String sql = "SELECT * FROM voucher";
        ResultSet vouchers = null;
        try {
            statement = jdbc_connection.prepareStatement(sql);
            vouchers = statement.executeQuery();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vouchers;
    }

}
