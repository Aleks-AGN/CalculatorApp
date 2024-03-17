package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

// import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
// import androidx.core.graphics.Insets;
// import androidx.core.view.ViewCompat;
// import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNum1, etNum2, etOperation;
    private TextView tvResultText;
    private Button btnCalculateResult;
    private Toast toastError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //     Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //     return insets;
        // });
        etNum1 = findViewById(R.id.num1_edit_text);
        etNum2 = findViewById(R.id.num2_edit_text);
        etOperation = findViewById(R.id.operation_edit_text);
        tvResultText = findViewById(R.id.result_text_view);
        btnCalculateResult = findViewById(R.id.calculate_result_button);
        btnCalculateResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float num1, num2, result = 0;
        boolean correctOperation = true;
        String operation = "";
        try {
            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());
            operation = etOperation.getText().toString();
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) throw new ArithmeticException();
                    result = num1 / num2;
                    break;
                default:
                    correctOperation = false;
                    break;
            }
        } catch (ArithmeticException e) {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null) {
                toastError.cancel();
            }
            toastError = Toast.makeText(this,R.string.divide_zero, duration);
            toastError.show();
            return;
        } catch (NullPointerException e) {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null) {
                toastError.cancel();
            }
            toastError = Toast.makeText(this,R.string.null_data, duration);
            toastError.show();
            return;
        } catch (NumberFormatException e) {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null) {
                toastError.cancel();
            }
            toastError = Toast.makeText(this,R.string.wrong_format, duration);
            toastError.show();
            return;
        }

        if (correctOperation) {
            tvResultText.setText(num1 + " " + operation + " " + num2 + " = " + result);
        } else {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null) {
                toastError.cancel();
            }
            toastError = Toast.makeText(this,R.string.wrong_operation, duration);
            toastError.show();
        }
    }
}