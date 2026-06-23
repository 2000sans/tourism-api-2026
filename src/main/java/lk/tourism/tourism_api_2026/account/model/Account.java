package lk.tourism.tourism_api_2026.account.model;

import jakarta.persistence.*;
import lk.tourism.tourism_api_2026.account.model.enums.AccountStatus;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String accessLevel;
    private String accountCode;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

}
