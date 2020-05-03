package pl.mswierczewski.skyfly.models.enums;

public enum AirplaneType {

    REGIONAL(300, 2000),
    NARROW_BODY(500, 6500),
    WIDE_BODY(800, 15000);

    private int avgSpeed; //value in km/h
    private int range;    //value in km

    AirplaneType(int avgSpeed, int range){
        this.avgSpeed = avgSpeed;
        this.range = range;
    }

    public int getAvgSpeed(){
        return avgSpeed;
    }

    public int getRange(){
        return range;
    }
}
