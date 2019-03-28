package com.wissensalt.rnd.springbootsoap.endpoint;

import com.wissensalt.employee.*;
import com.wissensalt.rnd.springbootsoap.dao.DepartmentDAO;
import com.wissensalt.rnd.springbootsoap.dao.EmployeeDAO;
import com.wissensalt.rnd.springbootsoap.exception.EmployeeNotFoundException;
import com.wissensalt.rnd.springbootsoap.model.DepartmentModel;
import com.wissensalt.rnd.springbootsoap.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

@Endpoint
public class EmployeeEndPoint {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private DepartmentDAO departmentDAO;

	private static final String NAMESPACE = "http://wissensalt.com/Employee";

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "EmployeeRequest")
	public EmployeeResponse findEmployee(@RequestPayload EmployeeRequest p_EmployeeRequest) throws EmployeeNotFoundException{
		EmployeeResponse response = new EmployeeResponse();
		Optional<EmployeeModel> employeeModel = employeeDAO.findById(p_EmployeeRequest.getId());
		if (employeeModel.get() != null) {
			response.setEmployee(mapper(employeeModel.get()));
		}else {
			throw new EmployeeNotFoundException("Employee Not Found With Id "+p_EmployeeRequest.getId());
		}
		return response;
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "EmployeeFindAllRequest")
	public EmployeeFindAllResponse findAllEmployee(@RequestPayload EmployeeFindAllRequest request) {
		EmployeeFindAllResponse result = new EmployeeFindAllResponse();
		List<EmployeeModel> employeeModels = employeeDAO.findAll();
		for (EmployeeModel model : employeeModels) {
			result.getEmployee().add(mapper(model));
		}

		return result;
	}

	@Transactional
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "InsertEmployeeRequest")
	public InsertEmployeeResponse insertEmployee(@RequestPayload InsertEmployeeRequest request) {
		EmployeeModel model = new EmployeeModel();
		model.setCode(request.getCode());
		model.setName(request.getName());
		model.setRemarks(request.getRemarks());

		if (request.getDepartmentId() != null) {
			DepartmentModel department = departmentDAO.findById(request.getDepartmentId()).get();
			if (department != null) {
				model.setDepartment(department);
			}
		}

		employeeDAO.save(model);
		InsertEmployeeResponse response = new InsertEmployeeResponse();
		response.setResponseCode("200");
		response.setResponseMsg("Success");
		return response;
	}

	@Transactional
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "DeleteEmployeeRequest")
	public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest p_DeleteEmployeeRequest) {
		DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		employeeDAO.deleteById(p_DeleteEmployeeRequest.getId());
		response.setResponseCode("200");
		response.setResponseMsg("Success");
		return response;
	}

	@Transactional
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "UpdateEmployeeRequest")
	public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest p_Request) {
		UpdateEmployeeResponse response = new UpdateEmployeeResponse();
		EmployeeModel model = employeeDAO.findById(p_Request.getId()).get();
		if (model != null) {
			if (p_Request.getCode() != null && p_Request.getCode().length() > 0) {
				model.setCode(p_Request.getCode());
			}
			if (p_Request.getName() != null && p_Request.getName().length() > 0) {
				model.setName(p_Request.getName());
			}
			if (p_Request.getRemarks() != null && p_Request.getRemarks().length() > 0) {
				model.setRemarks(p_Request.getRemarks());
			}
			if (p_Request.getDepartmentId() != null) {
				DepartmentModel department = departmentDAO.findById(p_Request.getDepartmentId()).get();
				if (department != null) {
					model.setDepartment(department);
				}
			}

			response.setResponseCode("200");
			response.setResponseMsg("Success");
		}else {
			response.setResponseCode("500");
			response.setResponseMsg("Failed to get Employee with Id "+ p_Request.getId());
		}
		return response;
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "FindEmployeeCompleteRequest")
	public FindEmployeeCompleteResponse findEmployeeComplete(@RequestPayload FindEmployeeCompleteRequest p_Request) {
		FindEmployeeCompleteResponse response = new FindEmployeeCompleteResponse();
		EmployeeModel emp = employeeDAO.findById(p_Request.getId()).get();
		if (emp != null) {
			response.setId(emp.getId());
			response.setCode(emp.getCode());
			response.setName(emp.getName());
			response.setRemarks(emp.getRemarks());

			DepartmentModel departmentModel = emp.getDepartment();
			if (departmentModel != null) {
				response.setDepartment(mapper(departmentModel));
			}
		}
		return response;
	}


	private Employee mapper(EmployeeModel employeeModel) {
		Employee employee = new Employee();
		employee.setId(employeeModel.getId());
		employee.setCode(employeeModel.getCode());
		employee.setName(employeeModel.getName());
		employee.setRemarks(employeeModel.getRemarks());
		return employee;
	}

	private Department mapper(DepartmentModel departmentModel) {
		Department department = new Department();
		department.setId(departmentModel.getId());
		department.setCode(departmentModel.getCode());
		department.setName(departmentModel.getName());
		department.setRemarks(departmentModel.getRemarks());
		return department;
	}
}
