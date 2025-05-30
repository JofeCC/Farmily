package com.example.farmily;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import model.Cart;
import model.Listing;

public class CartActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private Cart cart;
    private TextView totalPriceTextView;
    float totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Initialize();
    }

    private void Initialize() {
        tableLayout = findViewById(R.id.tableLayout);
        Button buttonAccount = findViewById(R.id.buttonReturn);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        Button buttonProceedToPayment = findViewById(R.id.buttonProceedToPayment);

        cart = (Cart) getIntent().getSerializableExtra("CART");
        if (cart != null) {
            displayCartItems();
        } else {
            Toast.makeText(this, "No items in the cart", Toast.LENGTH_SHORT).show();
        }

        buttonAccount.setOnClickListener(v -> finish());

        // Set up click listener for the Proceed to Payment button
        buttonProceedToPayment.setOnClickListener(v -> {
            proceedToPayment();
        });
    }

    private void displayCartItems() {
        ArrayList<Listing> productList = cart.getProductList();
        tableLayout.removeAllViews(); // Clear the table before adding

        totalPrice = 0;

        // Add table headers
        TableRow headerRow = new TableRow(this);
        headerRow.addView(createTextView("Item"));
        headerRow.addView(createTextView("Quantity"));
        headerRow.addView(createTextView("Price"));
        headerRow.addView(createTextView("Actions")); // For the remove button
        tableLayout.addView(headerRow);

        for (int i = 0; i < productList.size(); i++) {
            Listing listing = productList.get(i);
            TableRow row = new TableRow(this);

            TextView title = createTextView(listing.getTitle());
            TextView quantity = createTextView(String.valueOf(listing.getQuantity()));
            TextView price = createTextView(String.valueOf(listing.getPrice()));
            Button buttonRemove = new Button(this);
            buttonRemove.setText("Remove");

            row.addView(title);
            row.addView(quantity);
            row.addView(price);
            row.addView(buttonRemove);
            tableLayout.addView(row);

            // Calculate total price
            totalPrice += listing.getPrice() * listing.getQuantity();

            // Set the remove button's click listener
            buttonRemove.setOnClickListener(v -> {
                cart.removeFromCart(listing); // Implement this method in your Cart class
                displayCartItems(); // Refresh the cart display
                Toast.makeText(this, "Removed " + listing.getTitle() + " from cart", Toast.LENGTH_SHORT).show();
            });
        }

        // Display total price
        DecimalFormat df = new DecimalFormat("#.00");
        totalPriceTextView.setText("Total: $" + df.format(totalPrice));
    }

    private void proceedToPayment() {
        Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
        intent.putExtra("CART", cart); // Pass the cart object to PaymentActivity
        intent.putExtra("TOTAL_PRICE", totalPrice);
        startActivity(intent);
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(16, 16, 16, 16);
        return textView;
    }
}