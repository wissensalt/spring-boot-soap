package com.wissensalt.rnd.springbootsoap.dao;

import com.wissensalt.rnd.springbootsoap.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * Created on 3/27/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface EmployeeDAO extends JpaRepository<EmployeeModel, BigInteger> {
}
