package com.wissensalt.rnd.springbootsoap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created on 3/27/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
@Entity
@Table(name = "employee")
public class EmployeeModel implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 3366526377489665461L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnore
    private DepartmentModel department;
}
