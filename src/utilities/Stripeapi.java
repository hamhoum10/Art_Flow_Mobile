package utilities;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import java.util.HashMap;
import java.util.Map;

public class Stripeapi {
    public static boolean creditcardvalid;

        public boolean verifyCardAndPay(String cardNumber, int expMonth, int expYear, String cvc , String cardholderName ,String montant ) throws StripeException {


            Stripe.apiKey = "sk_test_51MgCPXKJx2ljBQl3EIxLiyZEKush4tOgcLj9PzUtbqP5vLDDOgyfRQVXxYJOJwF4w36CZpLLFBB8t71hZrTRZUCr00Q6asBogP";

            // Create a test token for a Visa card

            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", cardNumber);
            cardParams.put("exp_month", expMonth);
            cardParams.put("exp_year", 23);
            cardParams.put("cvc", cvc);
            cardParams.put("name", cardholderName);
            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("card", cardParams);

            try {

                Token token = Token.create(tokenParams);
                // Use the test token ID to create a charge
                Map<String, Object> chargeParams = new HashMap<>();
                chargeParams.put("amount", montant); // Charge amount in cents ya3ni namel *100
                chargeParams.put("currency", "usd"); // Charge currency
                chargeParams.put("description", "Test charge");
                chargeParams.put("source", token.getId());

                Charge charge = Charge.create(chargeParams);
                System.out.println("Charge succeeded!");
                creditcardvalid=true;
            } catch (StripeException e) {
                creditcardvalid=false;
                System.out.println("Charge failed: " + e.getMessage());
            }


            return creditcardvalid;
        }}




