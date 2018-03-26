package app.controller;

import app.dto.CreateBankCardDTO;
import app.model.BankCard;
import app.service.BankCardService;
import com.google.gson.Gson;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "/ATMAPI")
public class ApiController {

    @Autowired
    private BankCardService bankCardService;
    Gson gson =new Gson();

    @RequestMapping(value = "/createCard", method = RequestMethod.POST)
    public ResponseEntity<?> createCard(@RequestBody CreateBankCardDTO createBankCardDTO, UriComponentsBuilder ucBilder){

        bankCardService.createBankCard(createBankCardDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBilder.path("/ATMAPI/card/{id}").buildAndExpand(createBankCardDTO.getCard_number()).toUri());
        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCard(@PathVariable("id") String id){
        BankCard bankCard = bankCardService.getCardByNumber(id);
        return new ResponseEntity<String>(gson.toJson(bankCard), HttpStatus.OK);
    }
}
