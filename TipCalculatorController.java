package application;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CANADA);
	private static final NumberFormat percent = NumberFormat.getPercentInstance(Locale.CANADA);
	
	private BigDecimal tipPercentage = BigDecimal.valueOf(0.15);

    @FXML
    private Label amountLabel;

    @FXML
    private TextField amountTextField;

    @FXML
    private Label percentageLabel;

    @FXML
    private Slider percentageSlider;

    @FXML
    private Label tipLabel;

    @FXML
    private TextField tipTextField;

    @FXML
    private Label totalLabel;

    @FXML
    private TextField totalTextField;
    
    @FXML
    private Label amountperpersonLabel;

    @FXML
    private TextField amountperpersonText;

    @FXML
    private Label noofpeopleLabel;

    @FXML
    private TextField noofpeopleText;

    @FXML
    void calculateButtonPressed(ActionEvent event) {
    	try {
    		BigDecimal amount = new BigDecimal(amountTextField.getText());
    		BigDecimal tip = amount.multiply(tipPercentage);
    		BigDecimal total = amount.add(tip);
    		BigDecimal noofpeople = new BigDecimal(noofpeopleText.getText());
    		BigDecimal amountperperson = total.divide(noofpeople);
    		
    		tipTextField.setText(currency.format(tip));
    		totalTextField.setText(currency.format(total));
    		amountperpersonText.setText(currency.format(amountperperson));
    	}
    catch (NumberFormatException ex) {
    	amountTextField.setText("Enter Amount");
    	amountTextField.selectAll();
    	amountTextField.requestFocus();
    }
    }
    
    public void initialize() {
    	currency.setRoundingMode(RoundingMode.HALF_UP);
    	
    	percentageSlider.valueProperty().addListener(
    			new ChangeListener <Number>() {
    				@Override
    				public void changed(ObservableValue<? extends Number> ov,
    						Number oldValue, Number newValue) {
    					tipPercentage = BigDecimal.valueOf(newValue.intValue()/100.0);
    					percentageLabel.setText(percent.format(tipPercentage));
    				}
    			});
    }

}
