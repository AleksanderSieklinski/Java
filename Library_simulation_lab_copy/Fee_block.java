package Library_simulation_lab_copy;

import java.time.LocalDate;

public abstract sealed class Fee_block permits Student, Faculty {
    int max_fee = 100;
    public abstract boolean Block_borrow(LocalDate today);
}