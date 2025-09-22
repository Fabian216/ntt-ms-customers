package ntt.ntt_ms_customers.exception;

public class UnsupportedCustomerTypeException extends RuntimeException{

    public UnsupportedCustomerTypeException(String type) {
        super("Tipo de cliente no soportado: " + type);
    }

}
