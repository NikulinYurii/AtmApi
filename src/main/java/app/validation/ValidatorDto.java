package app.validation;

import app.dto.AuthenticationBankCardDTO;
import app.dto.CreateBankCardDTO;
import app.exception.NullFieldException;
import app.exception.UncorrectCardNumberException;

public class ValidatorDto {

    public void valid(CreateBankCardDTO createBankCardDTO) throws UncorrectCardNumberException, NullPointerException, NullFieldException {

        if (createBankCardDTO.getUserName() == null) {
            throw new NullFieldException("user_name");
        }

        if (createBankCardDTO.getUserSurname() == null) {
            throw new NullFieldException("user_surname");
        }

        if (createBankCardDTO.getCardNumber().length() != 16) {
            throw new UncorrectCardNumberException("length must be 16 ");
        }

        if (createBankCardDTO.getUserBirthday() == null) {
            throw new NullFieldException("user_birthday");
        }

        if (createBankCardDTO.getSex() == null) {
            throw new NullFieldException("user_sex");
        }

        if (createBankCardDTO.getAddress() == null) {
            throw new NullFieldException("user_address");
        }
    }

    public void valid(AuthenticationBankCardDTO authenticationBankCardDTO) throws UncorrectCardNumberException, NullFieldException {

        if (authenticationBankCardDTO.getCardNumber().length() != 16) {
            throw new UncorrectCardNumberException("length must be 16 ");
        }

        if (authenticationBankCardDTO.getUserPass() == null) {
            throw new NullFieldException("pass");
        }
    }
}
