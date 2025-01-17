package com.kalitut.unitconverter;

public class UnitConverter {
    public double convert(double value, String category, String fromUnit, String toUnit) {
        switch (category) {
            case "Length":
                return convertLength(value, fromUnit, toUnit);
            case "Weight":
                return convertWeight(value, fromUnit, toUnit);
            case "Volume":
                return convertVolume(value, fromUnit, toUnit);
            case "Temperature":
                return convertTemperature(value, fromUnit, toUnit);
            case "Area":
                return convertArea(value, fromUnit, toUnit);
            case "Speed":
                return convertSpeed(value, fromUnit, toUnit);
            case "Time":
                return convertTime(value, fromUnit, toUnit);
            case "Energy":
                return convertEnergy(value, fromUnit, toUnit);
            case "Pressure":
                return convertPressure(value, fromUnit, toUnit);
            case "Data":
                return convertData(value, fromUnit, toUnit);
            default:
                return value;
        }
    }

    private double convertLength(double value, String fromUnit, String toUnit) {
        // Convert to meters first
        double meters;
        switch (fromUnit) {
            case "Kilometers":
                meters = value * 1000;
                break;
            case "Miles":
                meters = value * 1609.34;
                break;
            case "Feet":
                meters = value * 0.3048;
                break;
            case "Inches":
                meters = value * 0.0254;
                break;
            case "Yards":
                meters = value * 0.9144;
                break;
            default:
                meters = value;
                break;
        }

        // Convert from meters to target unit
        switch (toUnit) {
            case "Kilometers":
                return meters / 1000;
            case "Miles":
                return meters / 1609.34;
            case "Feet":
                return meters / 0.3048;
            case "Inches":
                return meters / 0.0254;
            case "Yards":
                return meters / 0.9144;
            default:
                return meters;
        }
    }

    private double convertWeight(double value, String fromUnit, String toUnit) {
        // Convert to grams first
        double grams;
        switch (fromUnit) {
            case "Kilograms":
                grams = value * 1000;
                break;
            case "Pounds":
                grams = value * 453.592;
                break;
            case "Ounces":
                grams = value * 28.3495;
                break;
            default:
                grams = value;
                break;
        }

        // Convert from grams to target unit
        switch (toUnit) {
            case "Kilograms":
                return grams / 1000;
            case "Pounds":
                return grams / 453.592;
            case "Ounces":
                return grams / 28.3495;
            default:
                return grams;
        }
    }

    private double convertVolume(double value, String fromUnit, String toUnit) {
        // Convert to liters first
        double liters;
        switch (fromUnit) {
            case "Milliliters":
                liters = value / 1000;
                break;
            case "Gallons":
                liters = value * 3.78541;
                break;
            case "Cups":
                liters = value * 0.236588;
                break;
            case "Fluid Ounces":
                liters = value * 0.0295735;
                break;
            default:
                liters = value;
                break;
        }

        // Convert from liters to target unit
        switch (toUnit) {
            case "Milliliters":
                return liters * 1000;
            case "Gallons":
                return liters / 3.78541;
            case "Cups":
                return liters / 0.236588;
            case "Fluid Ounces":
                return liters / 0.0295735;
            default:
                return liters;
        }
    }

    private double convertArea(double value, String fromUnit, String toUnit) {
        // Convert to square meters first
        double squareMeters;
        switch (fromUnit) {
            case "Square Kilometers":
                squareMeters = value * 1000000;
                break;
            case "Square Feet":
                squareMeters = value * 0.092903;
                break;
            case "Acres":
                squareMeters = value * 4046.86;
                break;
            case "Hectares":
                squareMeters = value * 10000;
                break;
            default:
                squareMeters = value;
                break;
        }

        // Convert from square meters to target unit
        switch (toUnit) {
            case "Square Kilometers":
                return squareMeters / 1000000;
            case "Square Feet":
                return squareMeters / 0.092903;
            case "Acres":
                return squareMeters / 4046.86;
            case "Hectares":
                return squareMeters / 10000;
            default:
                return squareMeters;
        }
    }

    private double convertSpeed(double value, String fromUnit, String toUnit) {
        // Convert to meters per second first
        double mps;
        switch (fromUnit) {
            case "Kilometers/Hour":
                mps = value * 0.277778;
                break;
            case "Miles/Hour":
                mps = value * 0.44704;
                break;
            case "Knots":
                mps = value * 0.514444;
                break;
            default:
                mps = value;
                break;
        }

        // Convert from mps to target unit
        switch (toUnit) {
            case "Kilometers/Hour":
                return mps / 0.277778;
            case "Miles/Hour":
                return mps / 0.44704;
            case "Knots":
                return mps / 0.514444;
            default:
                return mps;
        }
    }

    private double convertTime(double value, String fromUnit, String toUnit) {
        // Convert to seconds first
        double seconds;
        switch (fromUnit) {
            case "Minutes":
                seconds = value * 60;
                break;
            case "Hours":
                seconds = value * 3600;
                break;
            case "Days":
                seconds = value * 86400;
                break;
            case "Weeks":
                seconds = value * 604800;
                break;
            default:
                seconds = value;
                break;
        }

        // Convert from seconds to target unit
        switch (toUnit) {
            case "Minutes":
                return seconds / 60;
            case "Hours":
                return seconds / 3600;
            case "Days":
                return seconds / 86400;
            case "Weeks":
                return seconds / 604800;
            default:
                return seconds;
        }
    }

    private double convertEnergy(double value, String fromUnit, String toUnit) {
        // Convert to joules first
        double joules;
        switch (fromUnit) {
            case "Calories":
                joules = value * 4.184;
                break;
            case "Kilowatt-Hours":
                joules = value * 3600000;
                break;
            case "Electron Volts":
                joules = value * 1.602176634E-19;
                break;
            default:
                joules = value;
                break;
        }

        // Convert from joules to target unit
        switch (toUnit) {
            case "Calories":
                return joules / 4.184;
            case "Kilowatt-Hours":
                return joules / 3600000;
            case "Electron Volts":
                return joules / 1.602176634E-19;
            default:
                return joules;
        }
    }

    private double convertPressure(double value, String fromUnit, String toUnit) {
        // Convert to pascals first
        double pascals;
        switch (fromUnit) {
            case "Bar":
                pascals = value * 100000;
                break;
            case "PSI":
                pascals = value * 6894.76;
                break;
            case "Atmospheres":
                pascals = value * 101325;
                break;
            default:
                pascals = value;
                break;
        }

        // Convert from pascals to target unit
        switch (toUnit) {
            case "Bar":
                return pascals / 100000;
            case "PSI":
                return pascals / 6894.76;
            case "Atmospheres":
                return pascals / 101325;
            default:
                return pascals;
        }
    }

    private double convertData(double value, String fromUnit, String toUnit) {
        // Convert to bytes first
        double bytes;
        switch (fromUnit) {
            case "Kilobytes":
                bytes = value * 1024;
                break;
            case "Megabytes":
                bytes = value * 1048576;
                break;
            case "Gigabytes":
                bytes = value * 1073741824;
                break;
            case "Terabytes":
                bytes = value * 1099511627776L;
                break;
            default:
                bytes = value;
                break;
        }

        // Convert from bytes to target unit
        switch (toUnit) {
            case "Kilobytes":
                return bytes / 1024;
            case "Megabytes":
                return bytes / 1048576;
            case "Gigabytes":
                return bytes / 1073741824;
            case "Terabytes":
                return bytes / 1099511627776L;
            default:
                return bytes;
        }
    }

    private double convertTemperature(double value, String fromUnit, String toUnit) {
        // Convert to Celsius first
        double celsius;
        switch (fromUnit) {
            case "Fahrenheit":
                celsius = (value - 32) * 5/9;
                break;
            case "Kelvin":
                celsius = value - 273.15;
                break;
            default:
                celsius = value;
                break;
        }

        // Convert from Celsius to target unit
        switch (toUnit) {
            case "Fahrenheit":
                return (celsius * 9/5) + 32;
            case "Kelvin":
                return celsius + 273.15;
            default:
                return celsius;
        }
    }
} 