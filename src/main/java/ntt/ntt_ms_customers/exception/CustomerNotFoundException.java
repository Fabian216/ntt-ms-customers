package ntt.ntt_ms_customers.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String id) {
        super("No se encontró el cliente con id: " + id);
    }

}
