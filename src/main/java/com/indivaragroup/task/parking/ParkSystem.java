package com.indivaragroup.task.parking;

public class ParkSystem {
    public final int DAY_TOTAL_HOUR = 24;

    //motor:
    //1 jam pertama 5k
    //jam berikutnya 3k
    //max 30k
    public final int BIKE_ENTRY_COST = 5000;
    public final int BIKE_HOURLY_COST = 3000;
    public final int BIKE_MAX_COST = 30000;

    //mobil:
    //1 jam pertama 8k
    //jam berikutnya 5k
    //max 50k
    public final int CAR_ENTRY_COST = 8000;
    public final int CAR_HOURLY_COST = 5000;
    public final int CAR_MAX_COST = 50000;

    public int bikeTotalCost, carTotalCost, bikeParkedHours, carParkedHours;

    public int bikeEntryHour = 23;
    public int bikeExitHour = 2;

    public int carEntryHour = 23;
    public int carExitHour = 10;

    public void calculateTime() {
        if (bikeEntryHour == bikeExitHour) {
            bikeParkedHours = 1;
        } else {
            bikeParkedHours = (bikeExitHour - bikeEntryHour + DAY_TOTAL_HOUR) % DAY_TOTAL_HOUR;
        }

        if (carEntryHour == carExitHour) {
            carParkedHours = 1;
        } else {
            carParkedHours = (carExitHour - carEntryHour + DAY_TOTAL_HOUR) % DAY_TOTAL_HOUR;
        }
    }

    public void runMetering() {
        calculateTime();

        if (bikeParkedHours > 1){
            bikeTotalCost = BIKE_ENTRY_COST + (BIKE_HOURLY_COST * (bikeParkedHours - 1));

            if (bikeTotalCost > BIKE_MAX_COST){
                bikeTotalCost = BIKE_MAX_COST;
            }
        } else if (bikeParkedHours == 1) {
            bikeTotalCost = BIKE_ENTRY_COST;
        }

        if (carParkedHours > 1){
            carTotalCost = CAR_ENTRY_COST + (CAR_HOURLY_COST * (carParkedHours - 1));

            if (carTotalCost > CAR_MAX_COST){
                carTotalCost = CAR_MAX_COST;
            }
        } else if (carParkedHours == 1) {
            carTotalCost = CAR_ENTRY_COST;
        }

        System.out.println("Bike parking hours: " + bikeParkedHours);
        System.out.println("Bike total cost: " + bikeTotalCost);
        System.out.println("Car parking hours: " + carParkedHours);
        System.out.println("Car total cost: " + carTotalCost);
    }
}
