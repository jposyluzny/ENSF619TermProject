package Theatre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoucherDBController extends DBController{

    static VoucherDBController singleInstance = null;

    public static VoucherDBController getSingleInstance() {
        if(singleInstance == null){
            singleInstance = new VoucherDBController();
        }
        return singleInstance;
    }

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
