package pl.orange.NextDoorBook.address;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.address.DTO.AddressDTO;
import pl.orange.NextDoorBook.address.DTO.AddressDTOMapper;

@Controller
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final AddressDTOMapper addressDTOMapper;

    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(addressDTOMapper.apply(addressService.getAddressById(id)));
    }

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        addressService.addAddress(addressDTOMapper.apply(addressDTO));
        return ResponseEntity
                .status(200)
                .body(addressDTO);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> deleteAddressById(@PathVariable Long id){
        addressService.deleteAddressById(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    @PutMapping("/addresses")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO){
        return ResponseEntity
                .status(200)
                .body(addressDTOMapper.apply(addressService.updateAddress(addressDTOMapper.apply(addressDTO))));
    }


}
