package uz.library.takm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "LIB_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 8756300442409334315L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LIB_USER")
    @SequenceGenerator(name = "SEQ_LIB_USER", sequenceName = "SEQ_LIB_USER", allocationSize = 1)
    private Long id;

    @Column(name = "LOGIN", length = 32, unique = true, nullable = false)
    private String login;

    @Column(name = "PASSWORD", length = 256, nullable = false)
    private String password;

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 32, nullable = false)
    private String surname;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "SAVED_BOOKS",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> savedBooks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderBook> orderBooks;

}
