public enum Day {
    Monday("Weekday"),
    Tuesday("Weekday"),
    Wednesday("Weekday"),
    Thursday("Weekday"),
    Friday("Weekday"),
    Saturday("Weekend"),
    Sunday("Weekend");

    private final String dayType;

    Day(String dayType){
        this.dayType = dayType;
    }

    public String getDayType(){
        return dayType;
    }
}



