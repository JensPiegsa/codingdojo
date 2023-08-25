package medicineclash2;

import java.util.Date;

public class Prescription {
    
    private Date dispenseDate = new Date();
    private int daysSupply = 30;

    public Date getDispenseDate() {
        return dispenseDate;
    }

    public int getDaysSupply() {
        return daysSupply;
    }

    public Prescription(Date dispenseDate, int daysSupply) {
        this.dispenseDate = dispenseDate;
        this.daysSupply = daysSupply;
    }

}