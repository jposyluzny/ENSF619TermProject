package GUI;


import java.awt.GridLayout;
import java.time.LocalDate;
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
				
				if(chargeType==1) {
					result.add(email.getText());
					result.add(credit.getText());
					result.add(expiry.getText());
				}
				else if(chargeType==2) {
					result.add(credit.getText());
					result.add(expiry.getText());
				}
			}
		}
		else if(userType==2){ //2 means registered user view (saved payment and email info)
			panel = makeRegisteredPaymentWindow(price,chargeType);
			int pay = JOptionPane.showConfirmDialog(null,panel,"Please enter payment info", JOptionPane.OK_CANCEL_OPTION);
			if (pay == JOptionPane.OK_OPTION) {
				result.add("not null"); //add a dummy field to tell UIController the result is not null
			}
		}
		
		return result;
	}	
	
	
	public JPanel makePaymentWindow(double price, int chargeType) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,1));
		String prompt=null;
		if(chargeType==1) { //chargetype 1 = paying
			prompt = "Your total is: $";
			JLabel total = new JLabel(prompt+price);
			panel.add(total);
			panel.add(createInputPanel(new JLabel("Email"), email));
		}
		else if(chargeType==2) { //chargetype 2 = refund
			prompt = "Your refund is: $";
			JLabel total = new JLabel(prompt+price);
			panel.add(total);
		}

		panel.add(createInputPanel(new JLabel("Credit Card"), credit));
		panel.add(createInputPanel(new JLabel("Expiry Date"), expiry));
		
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
		
	//Check to see if credit card input is valid
	public boolean checkCreditCard(String card) {
		if(card.length()!=6) {
			return false;
		}
		else {
			try{
				int cardNum = Integer.parseInt(card);
				return true;
				
			}
			catch(NumberFormatException e) {
				return false;
			}
		}
	}
	
	//Check to see if expiry input is valid
	public boolean checkExpiryDate(String expiry) {
		try {
			LocalDate localDate = LocalDate.now();
			int currentDay = localDate.getDayOfMonth();
			int currentMonth = localDate.getMonthValue();
			int currentYear = localDate.getYear();

			String[] expiryInfo = expiry.split("/");
			if(expiryInfo.length > 3) {
				return false;
			}
			for(String element : expiryInfo) {
				Integer.parseInt(element);
			}
			
			int inputDay = Integer.parseInt(expiryInfo[0]);
			int inputMonth = Integer.parseInt(expiryInfo[1]);
			int inputYear = Integer.parseInt(expiryInfo[2]) + 2000;

			if(inputDay < 1 || inputDay > 31) {
				return false;
			}
			if(inputMonth < 1 || inputMonth > 12) {
				return false;
			}
			if(inputYear < currentYear) {
				return false;
			}
			if(inputYear == currentYear) {
				if(inputMonth < currentMonth) {
					return false;
				}
				else if(inputMonth == currentMonth) {
					if(inputDay < currentDay) {
						return false;
					}
				}
			}
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	
	//check to see if user email input is valid
	public boolean checkEmailAddress(String email) {
		try{
			String firstHalf = email.split("@")[0];
			String secondHalf = email.split("@")[1];
			String dotCom = secondHalf.substring(secondHalf.length() - 4);
			if(!dotCom.equals(".com")) {
				return false;
			}
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
		return true;
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
	public void displayVoucherAppliedMessage() {
		JOptionPane.showMessageDialog(null, "NOTE: You have a voucher! 15% discount has been automatically applied to your order");
	}
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
