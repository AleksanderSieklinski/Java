package library;

import java.time.LocalDate;

public sealed abstract class Fee_block permits Student, Faculty {
    int max_fee = 100;
    public abstract boolean Block_borrow(LocalDate today);
}
