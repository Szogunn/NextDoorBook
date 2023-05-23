package pl.orange.NextDoorBook.address;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.address.DTO.AddressDTO;

@Controller
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .map(addressDTO -> ResponseEntity
                        .status(200)
                        .body(addressDTO)
                ).orElseGet(() -> ResponseEntity
                        .status(404)
                        .build());
    }

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        addressService.addAddress(addressDTO);
        return ResponseEntity
                .status(200)
                .body(addressDTO);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> deleteAddressById(@PathVariable Long id){

        return addressService.deleteAddressById(id) ?
                ResponseEntity
                .status(200)
                .build()
                :ResponseEntity
                .status(404)
                .build();
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id,@RequestBody AddressDTO addressDTO){
        return addressService.updateAddress(id,addressDTO) ?
                ResponseEntity
                .status(200)
                .body(addressDTO)
                :ResponseEntity
                .status(404)
                .build() ;
    }


}
