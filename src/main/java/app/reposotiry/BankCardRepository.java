package app.reposotiry;

import app.model.BankCard;
import app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankCardRepository extends CrudRepository<BankCard, Integer> {

    List<BankCard> getAllBankCardByOrderByCardNumberAsc();

    BankCard getBankCardByCardNumber(String cardNumber);

    BankCard deleteByCardNumber(String cardNumber);

}
