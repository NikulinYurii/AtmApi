package app.service;

import app.dto.AuthenticationBankCardDTO;
import app.dto.CreateBankCardDTO;
import app.dto.TransferDTO;
import app.exeption.TransferExeption;
import app.model.BankCard;

import java.util.List;

public interface BankCardService {

    boolean create(CreateBankCardDTO dto);

    boolean authentication(AuthenticationBankCardDTO dto);

    boolean transferFromCardToCard(TransferDTO dto) throws TransferExeption;

    List<BankCard> allCards();

    BankCard getCardByNumber(String number);
}
