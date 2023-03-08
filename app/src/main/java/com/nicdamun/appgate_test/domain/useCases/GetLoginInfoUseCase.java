package com.nicdamun.appgate_test.domain.useCases;

import com.nicdamun.appgate_test.data.repository.AuthRepository;
import com.nicdamun.appgate_test.domain.models.LoginEventModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class GetLoginInfoUseCase {

    private final AuthRepository authRepository;

    public GetLoginInfoUseCase(final AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public List<LoginEventModel> invoke(final String username, final String password) {
        final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale.getDefault()
        );
        return authRepository.getLoginEvents(username, password).stream().map(loginEventDto -> {
                try {
                    return new LoginEventModel(
                        loginEventDto.getUsername(),
                        loginEventDto.getPassword(),
                        formatter.parse(loginEventDto.getTimeStamp())
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                    return new LoginEventModel(
                        loginEventDto.getUsername(),
                        loginEventDto.getPassword(),
                        null
                    );
                }
            }).collect(Collectors.toList());
    }
}
