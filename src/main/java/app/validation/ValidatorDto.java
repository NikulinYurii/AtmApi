package app.validation;

import app.dto.AuthenticationBankCardDTO;
import app.dto.CreateBankCardDTO;
import app.dto.TransferDTO;
import app.exeption.NullException;
import app.exeption.TransferAmountExeption;
import app.exeption.UncorrectCardNamberExeption;

public class ValidatorDto {

    public void valid(CreateBankCardDTO createBankCardDTO) throws UncorrectCardNamberExeption, NullPointerException, NullException {

        if (createBankCardDTO.getUser_name() == null) {
            throw new NullException("user_name");
        }

        if (createBankCardDTO.getUser_surname() == null) {
            throw new NullException("user_surname");
        }

        if (createBankCardDTO.getCard_number().length() != 16) {
            throw new UncorrectCardNamberExeption("length must be 16 ");
        }

        if (createBankCardDTO.getUser_birthday() == null) {
            throw new NullException("user_birthday");
        }

        if (createBankCardDTO.getSex() == null) {
            throw new NullException("user_sex");
        }

        if (createBankCardDTO.getAddress() == null) {
            throw new NullException("user_address");
        }
    }

    public void valid(AuthenticationBankCardDTO authenticationBankCardDTO) throws UncorrectCardNamberExeption, NullException {

        if (authenticationBankCardDTO.getCardNumber().length() != 16) {
            throw new UncorrectCardNamberExeption("length must be 16 ");
        }

        if (authenticationBankCardDTO.getUserPass() == null) {
            throw new NullException("pass");
        }
    }

    public void valid(TransferDTO transferDTO) throws UncorrectCardNamberExeption, TransferAmountExeption {

        if (transferDTO.getSenderCardNumber().length() != 16) {
            throw new UncorrectCardNamberExeption("sender card number length must be 16 ");
        }

        if (transferDTO.getRecipientCardNumber().length() != 16) {
            throw new UncorrectCardNamberExeption("recipient card number length must be 16 ");
        }

        if (transferDTO.getAmountForTranster() <= 0) {
            throw new TransferAmountExeption("must be > 0");
        }
    }
}
