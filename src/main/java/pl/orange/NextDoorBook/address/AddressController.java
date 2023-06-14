package pl.orange.NextDoorBook.address;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.address.DTO.AddressDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/addresses")
public class AddressController {
    private final AddressService addressService;


    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(addressService.getAddressById(id));
    }

    @PostMapping("")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity
                .status(200)
                .body(addressService.addAddress(addressDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressDTO> deleteAddressById(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(addressService.deleteAddressById(id));
    }

    @PutMapping("")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO){
        return ResponseEntity
                .status(200)
                .body(addressService.updateAddress(addressDTO));
    }


}
