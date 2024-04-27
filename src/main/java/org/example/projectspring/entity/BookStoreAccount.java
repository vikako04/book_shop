package org.example.projectspring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_store_account")
public class BookStoreAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_s_id")
    private Integer id;

    @Column(name = "balance")
    private int balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
