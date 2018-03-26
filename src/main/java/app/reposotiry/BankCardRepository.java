package app.reposotiry;

import app.model.BankCard;
import app.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankCardRepository extends CrudRepository<BankCard, String> {

    List<BankCard> getAllBankCardByOrderByCardNumberAsc();

    BankCard getBankCardByCardNumber(String cardNumber);

}
