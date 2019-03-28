package com.wissensalt.rnd.springbootsoap.dao;

import com.wissensalt.rnd.springbootsoap.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * Created on 3/28/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface DepartmentDAO extends JpaRepository<DepartmentModel, BigInteger> {
}
