package library;

import java.time.LocalDate;

public abstract class Fee_block {
    int max_fee = 100;
    public abstract boolean Block_borrow(LocalDate today);
}
