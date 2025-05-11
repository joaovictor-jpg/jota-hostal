package br.com.jota.Booking.http;

import br.com.jota.Booking.dtos.RoomDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("roomserver-ms")
public interface RoomClient {
    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{roomNumber}")
    RoomDetails getRoom(@PathVariable("roomNumber") Integer roomNumber);
}
