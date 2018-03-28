package app.service;

import app.dto.AuthenticationBankCardDTO;
import app.dto.CreateBankCardDTO;
import app.dto.TransferDTO;
import app.exception.TransferException;
import app.model.BankCard;

import java.util.List;

public interface BankCardService {

    boolean create(CreateBankCardDTO dto);

    boolean authentication(AuthenticationBankCardDTO dto);

    boolean transferFromCardToCard(TransferDTO dto) throws TransferException;

    List<BankCard> allCards();

    BankCard getCardByNumber(String number);

    boolean cardExits(String cardNumber);
}
