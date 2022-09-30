package com.anwar.helper;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Helper {
    public static String formatCurrency(BigDecimal value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}
