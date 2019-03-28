package com.wissensalt.rnd.springbootsoap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 3/28/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
@Entity
@Table(name = "department")
public class DepartmentModel implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -4512599621934419240L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "remarks")
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
    private Set<EmployeeModel> employeeModels = new HashSet<>();
}
