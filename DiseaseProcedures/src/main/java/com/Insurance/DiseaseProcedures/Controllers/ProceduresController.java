package com.Insurance.DiseaseProcedures.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Insurance.DiseaseProcedures.Models.DiseaseDetails;
import com.Insurance.DiseaseProcedures.Models.DiseaseProcedures;
import com.Insurance.DiseaseProcedures.Service.DiseaseProceduresService;

@Controller
public class ProceduresController {

	@Autowired(required = true)
	DiseaseProceduresService disc;

	@GetMapping(value = "/diseases")
	public String getDiseases(Model model) {
		List<DiseaseDetails> diseases = disc.getDiseases();
		System.out.println("jhjhjh");
		// int insId = inspId;
		// model.addAttribute("inspId", insId);
		model.addAttribute("diseases", diseases);
		return "diseaseDetails";
	}

	@GetMapping(value = "/procedures/{diseaseId}")
	public String getProceduresByDisId(@PathVariable(name = "diseaseId") String diseaseId, Model model) {
		System.out.println("jhjhjh");
		List<DiseaseProcedures> procedures = disc.getProceduresByDisId(Integer.parseInt(diseaseId));
		System.out.println("jhjhjh");
		System.out.println(procedures);
		model.addAttribute("procedures", procedures);
		return "diseaseProcedures";
	}

	@PostMapping("/addProcedure")
	@ResponseBody
	public String addDisease(
	    @RequestParam(name = "discId") String discId,
	    @RequestParam(name = "procName") String procName,
	    @RequestParam(name = "icdCode") String icdCode) {
	    System.out.println("start add");
	    int message = disc.addProcedure( Integer.parseInt(discId), procName, icdCode);

	    System.out.println("end add");
	    if (message == 1)
	        return "record added successfully";
	    else
	        return "error occurred while adding record";
	}


	@PostMapping("/deleteProcedure")
	@ResponseBody
	public String deleteProcedure(@RequestParam(name = "procId") String procId) {
		System.out.println("delete start" + procId);
		int message = disc.deleteDisease(Integer.parseInt(procId));
		System.out.println("delete complete");
		System.out.println(message);
		if (message == 1)
			return "record added successfully";
		else
			return "error occured while adding record";
	}
}