package GUI;


import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentUI {

	JTextField email = new JTextField(13);
	JTextField credit = new JTextField(10);
	JTextField expiry = new JTextField(10);
	JTextField voucher = new JTextField(10);
	int paymentType = 1; //1 means user is paying, 2 means user wants to cancel 
	
	public PaymentUI() {
		
	}
	
	public ArrayList<String> paymentInfoDialog(int userType, double price, int chargeType) {
		ArrayList<String> result = new ArrayList<String>();
		JPanel panel = null;
		email.setText("");
		credit.setText("");
		expiry.setText("");
		voucher.setText("");
		
		if(userType==1) { //1 means ordinary user view
			panel = makePaymentWindow(price,chargeType);
			int pay = JOptionPane.showConfirmDialog(null,panel,"Please enter payment info", JOptionPane.OK_CANCEL_OPTION);
			if (pay == JOptionPane.OK_OPTION) {
				result.add(email.getText());
				result.add(credit.getText());
				result.add(expiry.getText());
				
				if(chargeType==1) {
					displaySuccessPaymentMessage();
				}
				else {
					displaySuccessRefundMessage();
				}
			}
		}
		else if(userType==2){ //2 means registered user view (saved payment and email info)
			panel = makeRegisteredPaymentWindow(price,chargeType);
			int pay = JOptionPane.showConfirmDialog(null,panel,"Please enter payment info", JOptionPane.OK_CANCEL_OPTION);
		}
		
		return result;
	}	
	
	public JPanel makePaymentWindow(double price, int chargeType) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,1));
		String prompt=null;
		if(chargeType==1) { //chargetype 1 = paying
			prompt = "Your total is: $";
		}
		else if(chargeType==2) { //chargetype 2 = refund
			prompt = "Your refund is: $";
		}
		
		JLabel total = new JLabel(prompt+price);
		panel.add(total);
		panel.add(createInputPanel(new JLabel("Email"), email));
		panel.add(createInputPanel(new JLabel("Credit Card"), credit));
		panel.add(createInputPanel(new JLabel("Expiry Date"), expiry));
//		if(chargeType==1) {
//			panel.add(createInputPanel(new JLabel("Coupon code"), voucher));
//		}
		
		return panel;
	}
	
	protected JPanel createInputPanel(JLabel label, JTextField textField) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(label);
		panel.add(textField);
	    return panel;
	}
		
	public JPanel makeRegisteredPaymentWindow(double price, int chargeType) {
		JPanel panel = new JPanel();
		String prompt=null;
		if(chargeType==1) { //chargetype 1 = paying
			prompt = "Your total is: $";
		}
		else if(chargeType==2) { //chargetype 2 = refund
			prompt = "Your refund is: $";
		}
		
		JLabel total = new JLabel(prompt+price);
		panel.add(total);
		
		JLabel label = new JLabel(". Your payment info on file will be charged");
		panel.add(label);
		return panel;
	}
	
	
	//Dialog boxes
	public void displaySuccessPaymentMessage() {
		JOptionPane.showMessageDialog(null, "Successfully made payments");
	}
	public void displaySuccessRefundMessage() {
		JOptionPane.showMessageDialog(null, "Successfully refunded payment");
	}
	public void displayErrorMessage() {
		JOptionPane.showMessageDialog(null, "ERROR: cannot reserve ticket");
	}

}
