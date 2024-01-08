package library;

import java.time.LocalDate;

// This is a sealed abstract class that is a superclass of Student and Faculty.
// Its purpose is to provide a common interface for the two subclasses.

public sealed abstract class Fee_block permits Student, Faculty {
    int max_fee = 100;
    public abstract boolean Block_borrow(LocalDate today);
}
