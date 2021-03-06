package app.controller;

import app.dto.AuthenticationBankCardDTO;
import app.dto.CreateBankCardDTO;
import app.dto.TransferDTO;
import app.exception.NullFieldException;
import app.exception.TransferException;
import app.exception.UncorrectCardNumberException;
import app.model.BankCard;
import app.model.User;
import app.service.BankCardService;
import app.validation.ValidatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/ATMAPI")
public class ApiController {

    @Autowired
    private BankCardService bankCardService;

    private ValidatorDto validatorDto = new ValidatorDto();

    @RequestMapping(value = "/createCard", method = RequestMethod.POST)
    public ResponseEntity<?> createCard(@RequestBody CreateBankCardDTO createBankCardDTO) {
        if (bankCardService.cardExits(createBankCardDTO.getCardNumber())) {
            return new ResponseEntity<String>(HttpStatus.valueOf("card number exist"));
        } else {
            try {
                validatorDto.valid(createBankCardDTO);
                bankCardService.create(createBankCardDTO);
                return new ResponseEntity<BankCard>(bankCardService.getCardByNumber(createBankCardDTO.getCardNumber()), HttpStatus.CREATED);
            } catch (UncorrectCardNumberException uncorrectCardNumberExeption) {
                uncorrectCardNumberExeption.printStackTrace();
                return new ResponseEntity<String>(HttpStatus.valueOf("uncorret card number"));
            } catch (NullFieldException e) {
                e.printStackTrace();
                return new ResponseEntity<String>(HttpStatus.valueOf("some fields are not filled"));
            }
        }

    }

    @RequestMapping(value = "/card/{number}", method = RequestMethod.GET)
    public ResponseEntity<?> getCard(@PathVariable("number") String number) {
        if (bankCardService.cardExits(number)) {
            return new ResponseEntity<BankCard>(bankCardService.getCardByNumber(number), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.valueOf("card number not exist"));
        }
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
        } catch (NullFieldException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.valueOf("some fields are not filled"));
        } catch (UncorrectCardNumberException uncorrectCardNamberException) {
            uncorrectCardNamberException.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.valueOf("uncorret card number"));
        }
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public ResponseEntity<?> transfer(@RequestBody TransferDTO dto) {
        try {
            bankCardService.transferFromCardToCard(dto);
            BankCard bankCard = bankCardService.getCardByNumber(dto.getSenderCardNumber());
            return new ResponseEntity<BankCard>(bankCard, HttpStatus.OK);
        } catch (TransferException transferException) {
            if (transferException.getMessage().equals("there is not enough money to transfer")) {
                return new ResponseEntity<String>(HttpStatus.valueOf(406));
            } else {
                return new ResponseEntity<String>(HttpStatus.valueOf(401));
            }
        }
    }
}
