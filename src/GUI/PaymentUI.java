package GUI;


import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentUI {

	JTextField email = new JTextField(13);
	JTextField credit = new JTextField(10);
	JTextField expiry = new JTextField(10);
	JTextField voucher = new JTextField(10);
	
	
	public PaymentUI() {
		
	}
	
	public String[] paymentInfoDialog(int key, int price) {
		String[] result = new String[4];
		JPanel panel = null;
		if(key==1) { //1 means ordinary user view
			panel = makePaymentWindow(price);
			int pay = JOptionPane.showConfirmDialog(null,panel,"Please enter payment info", JOptionPane.OK_CANCEL_OPTION);
			if (pay == JOptionPane.OK_OPTION) {
				result[0] = email.getText();
				result[1] = credit.getText();
				result[2] = expiry.getText();
				result[3] = voucher.getText();			
			}
		}
		else if(key==2){ //2 means registered user view (saved payment and email info)
			panel = makeRegisteredPaymentWindow(price);
			int pay = JOptionPane.showConfirmDialog(null,panel,"Please enter payment info", JOptionPane.OK_CANCEL_OPTION);
		}
		
		return result;
	}	
	
	public JPanel makePaymentWindow(int price) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,1));
		JLabel total = new JLabel("Your total is: $"+price);
		panel.add(total);
		panel.add(createInputPanel(new JLabel("Email"), email));
		panel.add(createInputPanel(new JLabel("Credit Card"), credit));
		panel.add(createInputPanel(new JLabel("Expiry Date"), expiry));
		panel.add(createInputPanel(new JLabel("Coupon code"), voucher));
		return panel;
	}
	
	protected JPanel createInputPanel(JLabel label, JTextField textField) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(label);
		panel.add(textField);
	    return panel;
	}
		
	public JPanel makeRegisteredPaymentWindow(int price) {
		JPanel panel = new JPanel();
		JLabel total = new JLabel("Your total is: $"+price);
		panel.add(total);
		
		JLabel label = new JLabel(". Your payment info on file will be charged");
		panel.add(label);
		return panel;
	}
	
	
	
	public void displaySuccessMessage() {
		JOptionPane.showMessageDialog(null, "Successfully reserved ticket");
	}
	public void displayErrorMessage() {
		JOptionPane.showMessageDialog(null, "ERROR: cannot reserve ticket");
	}

}
