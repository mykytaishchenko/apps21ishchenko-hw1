package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {
    double avgTemp, devTemp, minTemp, maxTemp;
    TempSummaryStatistics(double avgTemp, double devTemp, double minTemp, double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
}
