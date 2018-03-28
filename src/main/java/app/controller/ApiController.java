package app.controller;

import app.dto.AuthenticationBankCardDTO;
import app.dto.CreateBankCardDTO;
import app.dto.TransferDTO;
import app.exeption.NullException;
import app.exeption.TransferExeption;
import app.exeption.UncorrectCardNamberExeption;
import app.model.BankCard;
import app.model.User;
import app.service.BankCardService;
import app.validation.ValidatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping(value = "/ATMAPI")
public class ApiController {

    @Autowired
    private BankCardService bankCardService;

    private ValidatorDto validatorDto = new ValidatorDto();

    @RequestMapping(value = "/createCard", method = RequestMethod.POST)
    public ResponseEntity<?> createCard(@RequestBody CreateBankCardDTO createBankCardDTO, UriComponentsBuilder ucBilder) {
//todo card exist
        try {
            validatorDto.valid(createBankCardDTO);

            bankCardService.create(createBankCardDTO);
            //HttpHeaders httpHeaders = new HttpHeaders();
            //httpHeaders.setLocation(ucBilder.path("/ATMAPI/card/{id}").buildAndExpand(createBankCardDTO.getCard_number()).toUri());
            return new ResponseEntity<BankCard>(bankCardService.getCardByNumber(createBankCardDTO.getCard_number())/*httpHeaders*/, HttpStatus.CREATED);

        } catch (UncorrectCardNamberExeption uncorrectCardNumberExeption) {
            uncorrectCardNumberExeption.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.valueOf("uncorret card number"));
        } catch (NullException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.valueOf("some fields are not filled"));
        }

    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCard(@PathVariable("id") String id) {
        BankCard bankCard = bankCardService.getCardByNumber(id);
        return new ResponseEntity<BankCard>((bankCard), HttpStatus.OK);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCards() {

        List<BankCard> bankCards = bankCardService.allCards();

        if (bankCards.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<BankCard>>(bankCards, HttpStatus.OK);
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<?> authentication(@RequestBody AuthenticationBankCardDTO dto) {

        try {
            validatorDto.valid(dto);
            if ((bankCardService.getCardByNumber(dto.getCardNumber()) != null) &&
                    (bankCardService.getCardByNumber(dto.getCardNumber()).getPass()).equals(dto.getUserPass())) {
                return new ResponseEntity<String>(HttpStatus.valueOf(200));
            } else return new ResponseEntity<String>(HttpStatus.valueOf(401));
        } catch (NullException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.valueOf("some fields are not filled"));
        } catch (UncorrectCardNamberExeption uncorrectCardNamberExeption) {
            uncorrectCardNamberExeption.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.valueOf("uncorret card number"));
        }
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public ResponseEntity<?> transfer(@RequestBody TransferDTO dto, UriComponentsBuilder ucBilder) {

        try {
            bankCardService.transferFromCardToCard(dto);
            User user = bankCardService.getCardByNumber(dto.getSenderCardNumber()).getUser();
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (TransferExeption transferExeption) {
            transferExeption.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.valueOf("transfer problem"));
        }
    }


}
