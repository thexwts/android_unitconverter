package com.kalitut.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.AdapterView;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ConverterViewModel viewModel;
    private TextInputEditText inputValue;
    private TextView resultText;
    private AutoCompleteTextView categorySpinner;
    private AutoCompleteTextView fromUnitSpinner;
    private AutoCompleteTextView toUnitSpinner;
    private ArrayAdapter<String> categoryAdapter;
    private ArrayAdapter<String> unitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ConverterViewModel.class);
        
        initializeViews();
        setupToolbar();
        setupAdapters();
        setupSpinners();
        setupListeners();
        observeViewModel();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initializeViews() {
        inputValue = findViewById(R.id.inputValue);
        resultText = findViewById(R.id.resultText);
        categorySpinner = findViewById(R.id.categorySpinner);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        
        MaterialButton copyButton = findViewById(R.id.copyButton);
        copyButton.setOnClickListener(v -> copyResultToClipboard());
    }

    private void setupAdapters() {
        categoryAdapter = new ArrayAdapter<>(
            this,
            R.layout.dropdown_menu_popup_item,
            viewModel.getCategories()
        );

        // Initialize unit adapter with empty list (will be updated when category is selected)
        unitAdapter = new ArrayAdapter<>(
            this,
            R.layout.dropdown_menu_popup_item,
            viewModel.getUnitsForCategory(viewModel.getCategories().get(0))
        );
    }

    private void setupSpinners() {
        categorySpinner.setAdapter(categoryAdapter);
        fromUnitSpinner.setAdapter(unitAdapter);
        toUnitSpinner.setAdapter(unitAdapter);

        // Set default values
        categorySpinner.setText(categoryAdapter.getItem(0), false);
        fromUnitSpinner.setText(unitAdapter.getItem(0), false);
        toUnitSpinner.setText(unitAdapter.getItem(1), false); // Set second unit as default "to" unit
    }

    private void setupListeners() {
        // Use debounce for text changes to avoid excessive calculations
        inputValue.addTextChangedListener(new TextWatcher() {
            private Runnable runnable;
            private final long DELAY = 300; // milliseconds

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (runnable != null) {
                    inputValue.removeCallbacks(runnable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                runnable = () -> convert();
                inputValue.postDelayed(runnable, DELAY);
            }
        });

        categorySpinner.setOnItemClickListener((parent, view, position, id) -> {
            String category = parent.getItemAtPosition(position).toString();
            updateUnitSpinners(category);
            convert();
        });

        fromUnitSpinner.setOnItemClickListener((parent, view, position, id) -> convert());
        toUnitSpinner.setOnItemClickListener((parent, view, position, id) -> convert());
    }

    private void updateUnitSpinners(String category) {
        // Create new adapter instead of modifying existing one
        unitAdapter = new ArrayAdapter<>(
            this,
            R.layout.dropdown_menu_popup_item,
            viewModel.getUnitsForCategory(category)
        );
        
        fromUnitSpinner.setAdapter(unitAdapter);
        toUnitSpinner.setAdapter(unitAdapter);
        
        // Set default values
        if (unitAdapter.getCount() > 0) {
            fromUnitSpinner.setText(unitAdapter.getItem(0), false);
            if (unitAdapter.getCount() > 1) {
                toUnitSpinner.setText(unitAdapter.getItem(1), false);
            } else {
                toUnitSpinner.setText(unitAdapter.getItem(0), false);
            }
        }
    }

    private void observeViewModel() {
        viewModel.getResult().observe(this, result -> {
            resultText.setText(result);
        });
    }

    private void convert() {
        String value = inputValue.getText().toString();
        if (!value.isEmpty()) {
            try {
                viewModel.convert(
                    Double.parseDouble(value),
                    categorySpinner.getText().toString(),
                    fromUnitSpinner.getText().toString(),
                    toUnitSpinner.getText().toString()
                );
            } catch (NumberFormatException e) {
                resultText.setText(R.string.invalid_input);
            }
        } else {
            resultText.setText(R.string.error_empty_value);
        }
    }

    private void copyResultToClipboard() {
        String result = resultText.getText().toString();
        if (!result.isEmpty() && 
            !result.equals(getString(R.string.invalid_input)) && 
            !result.equals(getString(R.string.error_empty_value))) {
            
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Conversion Result", result);
            clipboard.setPrimaryClip(clip);
            
            Snackbar.make(resultText, R.string.copied_to_clipboard, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up resources
        categoryAdapter = null;
        unitAdapter = null;
    }
} 