package com.example.paymentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    Button pay;
    String TAG = "Payment Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Checkout.preload(getApplicationContext());


        pay = (Button) findViewById(R.id.pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });
    }

        public void startPayment() {
            /**
             * Instantiate Checkout
             */
            Checkout checkout = new Checkout();

            /**
             * Set your logo here
             */
//            checkout.setImage(R.drawable.logo);

            /**
             * Reference to current activity
             */
            final Activity activity = this;

            /**
             * Pass your payment options to the Razorpay Checkout as a JSONObject
             */
            try {
                JSONObject options = new JSONObject();


                /**
                 * Merchant Name
                 * eg: ACME Corp || HasGeek etc.
                 */
                options.put("name", "Razorpay Corp");

                /**
                 * Description can be anything
                 * eg: Order #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
                 *     Invoice Payment
                 *     etc.
                 */
                options.put("description", "Test order");
                options.put("order_id", "order_9A33XWu170gUtm");
                options.put("currency", "INR");

                /**
                 * Amount is always passed in currency subunits
                 * Eg: "500" = INR 5.00
                 */
                options.put("amount", "500");


                JSONObject preFill = new JSONObject();
                preFill.put("email", "test@razorpay.com");
                preFill.put("contact", "9953253689");

                options.put("prefill", preFill);


                checkout.open(activity, options);
            } catch(Exception e) {
                Log.e(TAG, "Error in starting Razorpay Checkout", e);
            }
        }



//    AccessToken accessToken = AccessToken.getCurrentAccessToken();
//    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();




    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(MainActivity.this, "PAYment sucessful", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(MainActivity.this, "PAYment Fail", Toast.LENGTH_SHORT).show();


    }
}
