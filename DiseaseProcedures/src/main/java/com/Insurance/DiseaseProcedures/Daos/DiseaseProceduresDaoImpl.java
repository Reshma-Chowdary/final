package com.Insurance.DiseaseProcedures.Daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.Insurance.DiseaseProcedures.Contractor.DiseaseProceduresDao;
import com.Insurance.DiseaseProcedures.Models.DiseaseDetails;
import com.Insurance.DiseaseProcedures.Models.DiseaseProcedures;
import com.Insurance.DiseaseProcedures.RowMappers.DiseaseDetailsRowMapper;
import com.Insurance.DiseaseProcedures.RowMappers.DiseaseProceduresRowMapper;

@Component
public class DiseaseProceduresDaoImpl implements DiseaseProceduresDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired(required = true)
	public DiseaseProceduresDaoImpl(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String GET_DISEASE_DETAILS = "Select * from diseases";
	String GET_PROCEDURES_BY_DIS_ID = "Select * from proceduress where proc_disc_id=?";

	@Override
	public List<DiseaseDetails> getDiseases() {
		return jdbcTemplate.query(GET_DISEASE_DETAILS, new DiseaseDetailsRowMapper());

	}

	@Override
	public List<DiseaseProcedures> getProceduresByDisId(int diseaseId) {
		return jdbcTemplate.query(GET_PROCEDURES_BY_DIS_ID, new Object[] { diseaseId }, new DiseaseProceduresRowMapper());
	}

	@Override
	public int addProcedure(int discId, String procName, String icdCode) {
		String ADD_PROCEDURE = "INSERT INTO PROCEDURESS (proc_disc_id, proc_name, proc_disc_icdcode) VALUES (?, ?, ?)";

		try {
			// Execute the query and retrieve the generated disease ID
			jdbcTemplate.queryForObject(ADD_PROCEDURE, Integer.class, discId, procName, icdCode);

		} catch (Exception e) {
			e.printStackTrace();
			return -1; // Return a negative value or handle the error as needed
		}
		return discId;
	}

	@Override
	public int delProcedure(int procId) {
		System.out.println("del-------"+procId);
		String DELETE_PROCEDURE = "DELETE FROM proceduress WHERE proc_id = ?";
	    jdbcTemplate.update(DELETE_PROCEDURE, procId);
	    return 1;
	}
}