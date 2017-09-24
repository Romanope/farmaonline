package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Romano on 14/09/2017.
 */

public class Util {

    public static String decimalFormat(BigDecimal valor) {

        DecimalFormat df = new DecimalFormat("#.00");

        valor = valor.setScale(2, BigDecimal.ROUND_HALF_UP);

        return df.format(valor);
    }

    public static boolean isNullOrEmpty(String text) {

        return (text == null) || (text.length() == 0);
    }
}
