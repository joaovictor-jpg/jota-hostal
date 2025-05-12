package br.com.jota.Booking.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if(response.status() == HttpStatus.NOT_FOUND.value()) {
            return new BusinessRuleException("Room not found");
        }
        return new Default().decode(s, response);
    }
}
