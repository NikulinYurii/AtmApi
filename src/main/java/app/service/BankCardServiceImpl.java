package app.service;

import app.dto.AuthenticationBankCardDTO;
import app.dto.CreateBankCardDTO;
import app.dto.TransferDTO;
import app.model.BankCard;
import app.model.User;
import app.reposotiry.BankCardRepository;
import app.reposotiry.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private BankCardRepository bankCardRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean createBankCard(CreateBankCardDTO dto) {

        //todo user exist??
        User user = new User(dto.getUser_name(), dto.getUser_surname(), dto.getUser_birthday(), dto.getSex(), dto.getAddress());
        BankCard bankCard = new BankCard(dto.getCard_number(), 0, dto.getCard_pass(), user);

        userRepository.save(user);
        bankCardRepository.save(bankCard);


        if (bankCardRepository.getBankCardByCardNumber(dto.getCard_number()) != null) return true;
        return false;
    }

    public boolean authenticationBankCard(AuthenticationBankCardDTO dto) {

        if (cardExits(dto.getCardNumber())) {
            return chekPass(dto.getCardNumber(), dto.getUserPass());
        } else return false;

    }

    //todo
    public boolean transferFromCardToCard(TransferDTO dto) {
        BankCard senderCard = bankCardRepository.getBankCardByCardNumber(dto.getSenderCardNumber());
        BankCard recipientCard = bankCardRepository.getBankCardByCardNumber(dto.getRecipientCardNumber());

        if (cardExits(dto.getRecipientCardNumber()) && cardExits(dto.getSenderCardNumber())) {
            if (authenticationBankCard(new AuthenticationBankCardDTO(dto.getSenderCardNumber(), dto.getSenderPass()))) {
                if (senderCard.getScore() >= dto.getAmountForTranster()) {
                    senderCard.setScore(senderCard.getScore() - dto.getAmountForTranster());
                    recipientCard.setScore(recipientCard.getScore() + dto.getAmountForTranster());
                }//недостатньо коштів для переведення
            }//не пройшла аутинтифікація
        }//не існує одна з карток

        return false;
    }

    public List<String> allCards() {

        Gson gson = new Gson();
        List<BankCard> bankCards = bankCardRepository.getAllBankCardByOrderByCardNumberAsc();
        List<String> ress = null;

        for (BankCard bankCard : bankCards) {
            ress.add(gson.toJson(bankCard));
        }

        return ress;
    }

    @Override
    public BankCard getCardByNumber(String number) {
        return bankCardRepository.getBankCardByCardNumber(number);
    }

    private boolean cardExits(String cardNumber) {
        BankCard card = bankCardRepository.getBankCardByCardNumber(cardNumber);

        if (card != null) {
            return true;
        } else return false;
    }

    private boolean chekPass(String cardNumber, String pass) {
        BankCard card = bankCardRepository.getBankCardByCardNumber(cardNumber);

        if (pass.equals(card.getPass())) {
            return true;
        } else return false;
    }
}
