package app.service;

import app.dto.AuthenticationBankCardDTO;
import app.dto.CreateBankCardDTO;
import app.dto.TransferDTO;
import app.exception.TransferException;
import app.model.BankCard;
import app.model.User;
import app.reposotiry.AddressRepository;
import app.reposotiry.BankCardRepository;
import app.reposotiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private BankCardRepository bankCardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    private final double defaultBankCardScore = 10;

    @Override
    public boolean create(CreateBankCardDTO dto) {

        User user = new User(dto.getUser_name(), dto.getUser_surname(), dto.getUser_birthday(), dto.getSex(), dto.getAddress());
        BankCard bankCard = new BankCard(dto.getCard_number(), defaultBankCardScore, dto.getCard_pass(), user);

        addressRepository.save(dto.getAddress());
        userRepository.save(user);
        bankCardRepository.save(bankCard);


        if (bankCardRepository.getBankCardByCardNumber(dto.getCard_number()) != null) return true;
        return false;
    }

    @Override
    public boolean authentication(AuthenticationBankCardDTO dto) {

        if (cardExits(dto.getCardNumber())) {
            return chekPass(dto.getCardNumber(), dto.getUserPass());
        } else return false;

    }

    @Override
    public boolean transferFromCardToCard(TransferDTO dto) throws TransferException {
        BankCard senderCard = bankCardRepository.getBankCardByCardNumber(dto.getSenderCardNumber());
        BankCard recipientCard = bankCardRepository.getBankCardByCardNumber(dto.getRecipientCardNumber());

        if (cardExits(dto.getRecipientCardNumber()) && cardExits(dto.getSenderCardNumber())) {
            if (authentication(new AuthenticationBankCardDTO(dto.getSenderCardNumber(), dto.getSenderPass()))) {
                if (senderCard.getScore() >= dto.getAmountForTransfer()) {

                    senderCard.setScore(senderCard.getScore() - dto.getAmountForTransfer());
                    recipientCard.setScore(recipientCard.getScore() + dto.getAmountForTransfer());

                    update(senderCard);
                    update(recipientCard);

                    return true;

                } else {
                    throw new TransferException("there is not enough money to transfer");
                }
            } else {
                throw new TransferException("has not passed the authentication");
            }
        } else {
            throw new TransferException("there is not one of the cards");
        }
    }

    @Override
    public List<BankCard> allCards() {

        List<BankCard> bankCards = bankCardRepository.getAllBankCardByOrderByCardNumberAsc();

        return bankCards;
    }

    @Override
    public BankCard getCardByNumber(String number) {
        return bankCardRepository.getBankCardByCardNumber(number);
    }

    private void update(BankCard bankCard) {

        bankCardRepository.deleteById(bankCard.getId());
        bankCardRepository.save(bankCard);

    }

    @Override
    public boolean cardExits(String cardNumber) {
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
