import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.format.datetime.DateFormatter;

import com.frsummit.HRM.api.exception.ApiValidationException;
import com.frsummit.HRM.api.generic.validator.ApiValidator;
import com.frsummit.HRM.api.generic.validator.DecimalMaxValidator;
import com.frsummit.HRM.api.generic.validator.DecimalMinValidator;
import com.frsummit.HRM.api.generic.validator.EmailValidator;
import com.frsummit.HRM.api.generic.validator.FutureValidator;
import com.frsummit.HRM.api.generic.validator.MaxValidator;
import com.frsummit.HRM.api.generic.validator.MinValidator;
import com.frsummit.HRM.api.generic.validator.NegativeOrZeroValidator;
import com.frsummit.HRM.api.generic.validator.NegativeValidator;
import com.frsummit.HRM.api.generic.validator.NotBlankValidator;
import com.frsummit.HRM.api.generic.validator.NotEmptyValidator;
import com.frsummit.HRM.api.generic.validator.NotNullValidator;
import com.frsummit.HRM.api.generic.validator.PastValidator;
import com.frsummit.HRM.api.generic.validator.PositiveOrZeroValidator;
import com.frsummit.HRM.api.generic.validator.PositiveValidator;
import com.frsummit.HRM.api.generic.validator.SizeValidator;

public class Main_7 {

    public static void main( String[] args ) throws IOException {

        System.out.println( "================================" );
        System.out.println( "= Email test" );
        System.out.println( "================================" );
        testEmail();
        System.out.println( "================================" );
        System.out.println( "= Decimal min test" );
        System.out.println( "================================" );
        testDecimalMin();
        System.out.println( "================================" );
        System.out.println( "= Decimal max test" );
        System.out.println( "================================" );
        testDecimalMax();
        System.out.println( "================================" );
        System.out.println( "= Min test" );
        System.out.println( "================================" );
        testMin();
        System.out.println( "================================" );
        System.out.println( "= Max test" );
        System.out.println( "================================" );
        testMax();
        System.out.println( "================================" );
        System.out.println( "= Future test" );
        System.out.println( "================================" );
        testFuture();
        System.out.println( "================================" );
        System.out.println( "= Past test" );
        System.out.println( "================================" );
        testPast();
        System.out.println( "================================" );
        System.out.println( "= Negative or zero" );
        System.out.println( "================================" );
        testNegativeOrZero();
        System.out.println( "================================" );
        System.out.println( "= Negative" );
        System.out.println( "================================" );
        testNegative();
        System.out.println( "================================" );
        System.out.println( "= Positive" );
        System.out.println( "================================" );
        testPositive();
        System.out.println( "================================" );
        System.out.println( "= Positive or zero" );
        System.out.println( "================================" );
        testPositiveOrZero();
        System.out.println( "================================" );
        System.out.println( "= Not blank" );
        System.out.println( "================================" );
        testNotBlank();
        System.out.println( "================================" );
        System.out.println( "= Not null" );
        System.out.println( "================================" );
        testNotNull();
        System.out.println( "================================" );
        System.out.println( "= Not empty" );
        System.out.println( "================================" );
        testNotEmpty();
        System.out.println( "================================" );
        System.out.println( "= Size" );
        System.out.println( "================================" );
        testSize();

    }

    public static void testEmail() {
        ApiValidator emailValidator = new EmailValidator( "email" );
        System.out.println( "henrifoko" + "..." + checkValidation( emailValidator, "henrifoko" ) );
        System.out.println( "henrifoko_@" + "..." + checkValidation( emailValidator, "henrifoko_@" ) );
        System.out.println( "@gmail.com" + "..." + checkValidation( emailValidator, "@gmail.com" ) );
        System.out.println( "henrifoko@gmail.com" + "..." + checkValidation( emailValidator, "henrifoko@gmail.com" ) );
        System.out.println( "s@." + "..." + checkValidation( emailValidator, "s@." ) );
        System.out.println( "@g." + "..." + checkValidation( emailValidator, "@g." ) );
        System.out.println( "c@g.d" + "..." + checkValidation( emailValidator, "c@g.d" ) );
        System.out.println( "p@k" + "..." + checkValidation( emailValidator, "p@k" ) );
    }

    public static void testDecimalMin() {
        ApiValidator decimalValidator = new DecimalMaxValidator( "bet", 12 );
        System.out.println( "12" + "..." + checkValidation( decimalValidator, 12 ) );
        System.out.println( "2.1" + "..." + checkValidation( decimalValidator, 2.1 ) );
        System.out.println( "5" + "..." + checkValidation( decimalValidator, 5 ) );
        System.out.println( "0.0" + "..." + checkValidation( decimalValidator, 0.0 ) );
        System.out.println( "123" + "..." + checkValidation( decimalValidator, 123 ) );
        System.out.println( "-0.0" + "..." + checkValidation( decimalValidator, -0.0 ) );
        System.out.println( "-9999999" + "..." + checkValidation( decimalValidator, -9999999 ) );
        System.out.println( "+∞" + "..." + checkValidation( decimalValidator, Double.POSITIVE_INFINITY ) );
        System.out.println( "MAX" + "..." + checkValidation( decimalValidator, Double.MAX_VALUE ) );
        System.out.println( "-∞" + "..." + checkValidation( decimalValidator, Double.NEGATIVE_INFINITY ) );
        System.out.println( "MIN" + "..." + checkValidation( decimalValidator, Double.MIN_VALUE ) );
    }

    private static void testDecimalMax() {
        ApiValidator decimalValidator = new DecimalMinValidator( "size", 12 );
        System.out.println( "12" + "..." + checkValidation( decimalValidator, 12 ) );
        System.out.println( "2.1" + "..." + checkValidation( decimalValidator, 2.1 ) );
        System.out.println( "5" + "..." + checkValidation( decimalValidator, 5 ) );
        System.out.println( "0.0" + "..." + checkValidation( decimalValidator, 0.0 ) );
        System.out.println( "123" + "..." + checkValidation( decimalValidator, 123 ) );
        System.out.println( "-0.0" + "..." + checkValidation( decimalValidator, -0.0 ) );
        System.out.println( "-9999999" + "..." + checkValidation( decimalValidator, -9999999 ) );
        System.out.println( "+∞" + "..." + checkValidation( decimalValidator, Double.POSITIVE_INFINITY ) );
        System.out.println( "MAX" + "..." + checkValidation( decimalValidator, Double.MAX_VALUE ) );
        System.out.println( "-∞" + "..." + checkValidation( decimalValidator, Double.NEGATIVE_INFINITY ) );
        System.out.println( "MIN" + "..." + checkValidation( decimalValidator, Double.MIN_VALUE ) );
    }

    private static void testMax() {
        ApiValidator integerValidator = new MaxValidator( "size", 12 );
        System.out.println( "12" + "..." + checkValidation( integerValidator, 12 ) );
        System.out.println( "2.1" + "..." + checkValidation( integerValidator, 2 ) );
        System.out.println( "5" + "..." + checkValidation( integerValidator, 5 ) );
        System.out.println( "0.0" + "..." + checkValidation( integerValidator, 0 ) );
        System.out.println( "123" + "..." + checkValidation( integerValidator, 123 ) );
        System.out.println( "-0.0" + "..." + checkValidation( integerValidator, -0 ) );
        System.out.println( "-9999999" + "..." + checkValidation( integerValidator, -9999999 ) );
        System.out.println( "MAX" + "..." + checkValidation( integerValidator, Integer.MAX_VALUE ) );
        System.out.println( "MIN" + "..." + checkValidation( integerValidator, Integer.MIN_VALUE ) );
    }

    private static void testMin() {
        ApiValidator integerValidator = new MinValidator( "size", 12 );
        System.out.println( "12" + "..." + checkValidation( integerValidator, 12 ) );
        System.out.println( "2.1" + "..." + checkValidation( integerValidator, 2 ) );
        System.out.println( "5" + "..." + checkValidation( integerValidator, 5 ) );
        System.out.println( "0.0" + "..." + checkValidation( integerValidator, 0 ) );
        System.out.println( "123" + "..." + checkValidation( integerValidator, 123 ) );
        System.out.println( "-0.0" + "..." + checkValidation( integerValidator, -0 ) );
        System.out.println( "-9999999" + "..." + checkValidation( integerValidator, -9999999 ) );
        System.out.println( "MAX" + "..." + checkValidation( integerValidator, Integer.MAX_VALUE ) );
        System.out.println( "MIN" + "..." + checkValidation( integerValidator, Integer.MIN_VALUE ) );
    }

    private static void testFuture() {
        ApiValidator dateValidator = new FutureValidator( "date" );

        System.out.println( "2018-01-01" + "..." + checkValidation( dateValidator, dateFromString( "2018-01-01" ) ) );
        System.out.println( "2022-08-12" + "..." + checkValidation( dateValidator, dateFromString( "2022-08-12" ) ) );
        System.out.println( "2019-07-11" + "..." + checkValidation( dateValidator, dateFromString( "2019-07-11" ) ) );
        System.out.println( "2021-09-23" + "..." + checkValidation( dateValidator, dateFromString( "2021-09-23" ) ) );
        System.out.println( "2021-08-10" + "..." + checkValidation( dateValidator, dateFromString( "2021-08-10" ) ) );
    }

    private static void testPast() {
        ApiValidator dateValidator = new PastValidator( "date" );

        System.out.println( "2018-01-01" + "..." + checkValidation( dateValidator, dateFromString( "2018-01-01" ) ) );
        System.out.println( "2022-08-12" + "..." + checkValidation( dateValidator, dateFromString( "2022-08-12" ) ) );
        System.out.println( "2019-07-11" + "..." + checkValidation( dateValidator, dateFromString( "2019-07-11" ) ) );
        System.out.println( "2021-09-23" + "..." + checkValidation( dateValidator, dateFromString( "2021-09-23" ) ) );
        System.out.println( "2021-08-10" + "..." + checkValidation( dateValidator, dateFromString( "2021-08-10" ) ) );
    }

    private static void testNegativeOrZero() {
        ApiValidator numberValidator = new NegativeOrZeroValidator( "number" );

        System.out.println( "12" + "..." + checkValidation( numberValidator, 12 ) );
        System.out.println( "2.1" + "..." + checkValidation( numberValidator, 2 ) );
        System.out.println( "5" + "..." + checkValidation( numberValidator, 5 ) );
        System.out.println( "0.0" + "..." + checkValidation( numberValidator, 0 ) );
        System.out.println( "123" + "..." + checkValidation( numberValidator, 123 ) );
        System.out.println( "-0.0" + "..." + checkValidation( numberValidator, -0 ) );
        System.out.println( "-9999999" + "..." + checkValidation( numberValidator, -9999999 ) );
        System.out.println( "MAX" + "..." + checkValidation( numberValidator, Integer.MAX_VALUE ) );
        System.out.println( "MIN" + "..." + checkValidation( numberValidator, Integer.MIN_VALUE ) );
    }

    private static void testPositiveOrZero() {
        ApiValidator numberValidator = new PositiveOrZeroValidator( "number" );

        System.out.println( "12" + "..." + checkValidation( numberValidator, 12 ) );
        System.out.println( "2.1" + "..." + checkValidation( numberValidator, 2 ) );
        System.out.println( "5" + "..." + checkValidation( numberValidator, 5 ) );
        System.out.println( "0.0" + "..." + checkValidation( numberValidator, 0 ) );
        System.out.println( "123" + "..." + checkValidation( numberValidator, 123 ) );
        System.out.println( "-0.0" + "..." + checkValidation( numberValidator, -0 ) );
        System.out.println( "-9999999" + "..." + checkValidation( numberValidator, -9999999 ) );
        System.out.println( "MAX" + "..." + checkValidation( numberValidator, Integer.MAX_VALUE ) );
        System.out.println( "MIN" + "..." + checkValidation( numberValidator, Integer.MIN_VALUE ) );
    }

    private static void testNegative() {
        ApiValidator numberValidator = new NegativeValidator( "number" );

        System.out.println( "12" + "..." + checkValidation( numberValidator, 12 ) );
        System.out.println( "2.1" + "..." + checkValidation( numberValidator, 2 ) );
        System.out.println( "5" + "..." + checkValidation( numberValidator, 5 ) );
        System.out.println( "0.0" + "..." + checkValidation( numberValidator, 0 ) );
        System.out.println( "123" + "..." + checkValidation( numberValidator, 123 ) );
        System.out.println( "-0.0" + "..." + checkValidation( numberValidator, -0 ) );
        System.out.println( "-9999999" + "..." + checkValidation( numberValidator, -9999999 ) );
        System.out.println( "MAX" + "..." + checkValidation( numberValidator, Integer.MAX_VALUE ) );
        System.out.println( "MIN" + "..." + checkValidation( numberValidator, Integer.MIN_VALUE ) );
    }

    private static void testPositive() {
        ApiValidator numberValidator = new PositiveValidator( "number" );

        System.out.println( "12" + "..." + checkValidation( numberValidator, 12 ) );
        System.out.println( "2.1" + "..." + checkValidation( numberValidator, 2 ) );
        System.out.println( "5" + "..." + checkValidation( numberValidator, 5 ) );
        System.out.println( "0.0" + "..." + checkValidation( numberValidator, 0 ) );
        System.out.println( "123" + "..." + checkValidation( numberValidator, 123 ) );
        System.out.println( "-0.0" + "..." + checkValidation( numberValidator, -0 ) );
        System.out.println( "-9999999" + "..." + checkValidation( numberValidator, -9999999 ) );
        System.out.println( "MAX" + "..." + checkValidation( numberValidator, Integer.MAX_VALUE ) );
        System.out.println( "MIN" + "..." + checkValidation( numberValidator, Integer.MIN_VALUE ) );
    }

    private static void testNotNull() {
        ApiValidator objectValidator = new NotNullValidator( "object" );

        System.out.println( "12" + "..." + checkValidation( objectValidator, "12" ) );
        System.out.println( 0 + "..." + checkValidation( objectValidator, 0 ) );
        System.out.println( null + "..." + checkValidation( objectValidator, null ) );
        System.out.println( new Object[0] + "..." + checkValidation( objectValidator, new Object[0] ) );
        System.out.println( "" + "..." + checkValidation( objectValidator, "" ) );
    }

    private static void testNotBlank() {
        ApiValidator stringValidator = new NotBlankValidator( "object" );

        System.out.println( "12" + "..." + checkValidation( stringValidator, "12" ) );
        System.out.println( " " + "..." + checkValidation( stringValidator, " " ) );
        System.out.println( "   " + "..." + checkValidation( stringValidator, "    " ) );
        System.out.println( "   _" + "..." + checkValidation( stringValidator, "    " ) );
        System.out.println( "\n" + "..." + checkValidation( stringValidator, "\n" ) );
        System.out.println( "\r" + "..." + checkValidation( stringValidator, "\r" ) );
        System.out.println( "\b" + "..." + checkValidation( stringValidator, "\b" ) );
    }

    private static void testNotEmpty() {
        ApiValidator stringValidator = new NotEmptyValidator( "object" );

        System.out.println( "12" + "..." + checkValidation( stringValidator, "12" ) );
        System.out.println( "" + "..." + checkValidation( stringValidator, "" ) );
        System.out.println( "  " + "..." + checkValidation( stringValidator, "  " ) );
        System.out.println( "  _ " + "..." + checkValidation( stringValidator, "  " ) );
        System.out.println( "   " + "..." + checkValidation( stringValidator, "  " ) );
        System.out.println( "\n" + "..." + checkValidation( stringValidator, "\n" ) );
        System.out.println( "\b" + "..." + checkValidation( stringValidator, "\b" ) );
        System.out.println( "\r" + "..." + checkValidation( stringValidator, "\r" ) );
    }

    public static void testSize() {
        List<Integer> list = Arrays.asList( 1, 2, 4, 6 );

        ApiValidator sizeValidator1 = new SizeValidator( "size", 2, null );
        System.out.println( "Bonjour comment allez vous" + "..."
                + checkValidation( sizeValidator1, "Bonjour comment allez vous" ) );
        System.out.println( new Object[1] + "..." + checkValidation( sizeValidator1, new Object[1] ) );
        System.out.println( list + "..." + checkValidation( sizeValidator1, list ) );

        ApiValidator sizeValidator2 = new SizeValidator( "size", null, 6 );
        System.out.println( "Bonjour comment allez vous" + "..."
                + checkValidation( sizeValidator2, "Bonjour comment allez vous" ) );
        System.out.println( new Object[1] + "..." + checkValidation( sizeValidator2, new Object[1] ) );
        System.out.println( list + "..." + checkValidation( sizeValidator2, list ) );

        ApiValidator sizeValidator3 = new SizeValidator( "size", 2, 6 );
        System.out.println( "Bonjour comment allez vous" + "..."
                + checkValidation( sizeValidator3, "Bonjour comment allez vous" ) );
        System.out.println( new Object[1] + "..." + checkValidation( sizeValidator3, new Object[1] ) );
        System.out.println( list + "..." + checkValidation( sizeValidator3, list ) );
    }

    public static Date dateFromString( String dateString ) {
        Date parsed = null;
        try {
            DateFormatter format = new DateFormatter( "yyyy-MM-dd" );
            parsed = format.parse( dateString, Locale.US );

        } catch ( ParseException pe ) {
            throw new IllegalArgumentException( pe );
        }
        return parsed;
    }

    public static boolean checkValidation( ApiValidator validator, Object value ) {
        try {
            return validator.validate( value );
        } catch ( ApiValidationException e ) {
            return false;
        }
    }
}
