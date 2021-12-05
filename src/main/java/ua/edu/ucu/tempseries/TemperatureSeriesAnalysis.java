package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private double[] tempSeries = new double[2];
    int length = 0;

    public TemperatureSeriesAnalysis() {
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        addTemps(temperatureSeries);
    }

    public double average() {
        emptyCheck();
        double sum = 0;

        for (int i = 0; i < length; i++) sum += tempSeries[i];
        return sum / length;
    }

    public double deviation() {
        emptyCheck();
        double sum = 0;
        double average = average();

        for (int i = 0; i < length; i++)
            sum += Math.pow(tempSeries[i] - average, 2);
        return sum / (length - 1);
    }

    public double min() {
        emptyCheck();

        double min = tempSeries[0];
        for (int i = 1; i < length; i++)
            if (tempSeries[i] < min) min = tempSeries[i];
        return min;
    }

    public double max() {
        emptyCheck();

        double max = tempSeries[0];
        for (int i = 1; i < length; i++)
            if (tempSeries[i] > max) max = tempSeries[i];
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        emptyCheck();

        double closest = tempSeries[0];
        for (int i = 1; i < length; i++)
            if (Math.abs(tempSeries[i] - tempValue) < Math.abs(closest - tempValue)
                    || (tempSeries[i] == -closest && tempSeries[i] > 0))
                closest = tempSeries[i];
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        emptyCheck();
        double[] arr = new double[length];

        int counter = 0;
        for (int i = 0; i < length; i++) {
            if (tempSeries[i] < tempValue) {
                arr[counter] = tempSeries[i];
                counter++;
            }
        }

        double[] correctSizeArr = new double[counter];
        for (int i = 0; i < counter; i++) correctSizeArr[i] = arr[i];
        return correctSizeArr;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        emptyCheck();
        double[] arr = new double[length];

        int counter = 0;
        for (int i = 0; i < length; i++) {
            if (tempSeries[i] >= tempValue) {
                arr[counter] = tempSeries[i];
                counter++;
            }
        }

        double[] correctSizeArr = new double[counter];
        for (int i = 0; i < counter; i++) correctSizeArr[i] = arr[i];
        return correctSizeArr;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temperature : temps) if (temperature < -273) throw new InputMismatchException();
        if (length + temps.length >= tempSeries.length) resize();
        for (int i = 0; i < temps.length; i++) tempSeries[i + length] = temps[i];
        length += temps.length;
        return length;
    }

    private void resize() {
        double[] newTempSeries;
        newTempSeries = new double[tempSeries.length * 2];
        for (int i = 0; i < tempSeries.length; i++) newTempSeries[i] = tempSeries[i];
        tempSeries = newTempSeries;
    }

    private void emptyCheck() {
        if (length < 1) throw new IllegalArgumentException();
    }
}
