package com.kalitut.unitconverter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverterViewModel extends ViewModel {
    private final MutableLiveData<String> result = new MutableLiveData<>();
    private final Map<String, List<String>> unitCategories = new HashMap<>();
    private final UnitConverter converter;

    public ConverterViewModel() {
        converter = new UnitConverter();
        initializeUnitCategories();
    }

    private void initializeUnitCategories() {
        unitCategories.put("Length", Arrays.asList("Meters", "Kilometers", "Miles", "Feet", "Inches", "Yards"));
        unitCategories.put("Weight", Arrays.asList("Kilograms", "Grams", "Pounds", "Ounces", "Tons"));
        unitCategories.put("Volume", Arrays.asList("Liters", "Milliliters", "Gallons", "Cups", "Fluid Ounces"));
        unitCategories.put("Temperature", Arrays.asList("Celsius", "Fahrenheit", "Kelvin"));
        unitCategories.put("Area", Arrays.asList("Square Meters", "Square Feet", "Acres", "Square Kilometers", "Hectares"));
        unitCategories.put("Speed", Arrays.asList("Kilometers/Hour", "Miles/Hour", "Meters/Second", "Knots"));
        unitCategories.put("Time", Arrays.asList("Seconds", "Minutes", "Hours", "Days", "Weeks"));
        unitCategories.put("Energy", Arrays.asList("Joules", "Calories", "Kilowatt-Hours", "Electron Volts"));
        unitCategories.put("Pressure", Arrays.asList("Pascals", "Bar", "PSI", "Atmospheres"));
        unitCategories.put("Data", Arrays.asList("Bytes", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes"));
    }

    public List<String> getCategories() {
        return Arrays.asList(
            "Length", "Weight", "Volume", "Temperature", "Area",
            "Speed", "Time", "Energy", "Pressure", "Data"
        );
    }

    public List<String> getUnitsForCategory(String category) {
        return unitCategories.get(category);
    }

    public void convert(double value, String category, String fromUnit, String toUnit) {
        double convertedValue = converter.convert(value, category, fromUnit, toUnit);
        result.setValue(String.format("%.2f %s = %.2f %s", value, fromUnit, convertedValue, toUnit));
    }

    public LiveData<String> getResult() {
        return result;
    }
} 