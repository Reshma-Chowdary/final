package com.Insurance.DiseaseProcedures.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Insurance.DiseaseProcedures.Contractor.DiseaseProceduresDao;
import com.Insurance.DiseaseProcedures.Models.DiseaseDetails;
import com.Insurance.DiseaseProcedures.Models.DiseaseProcedures;

@Service
public class DiseaseProceduresService {
	@Autowired(required = true)
	DiseaseProceduresDao procedureDao;

	public List<DiseaseDetails> getDiseases() {
		return procedureDao.getDiseases();
	}

	public List<DiseaseProcedures> getProceduresByDisId(int diseaseId) {
		return procedureDao.getProceduresByDisId(diseaseId);
	}

	public int addProcedure(int discId, String procName, String icdCode) {
		return procedureDao.addProcedure(discId,procName,icdCode);
	}

	public int deleteDisease(int procId) {
		// TODO Auto-generated method stub
		return procedureDao.delProcedure(procId);
	}
}