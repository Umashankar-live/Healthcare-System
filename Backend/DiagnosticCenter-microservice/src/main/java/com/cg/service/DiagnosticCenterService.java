package com.cg.service;

import java.util.List;

import com.cg.bean.DiagnosticCenters;



public interface DiagnosticCenterService {
	
	public DiagnosticCenters addCenter(DiagnosticCenters center);

	public void deleteCenter(Integer centerId);

	public DiagnosticCenters searchCenter(Integer centerId);

	public List<DiagnosticCenters> getAllCenter();
	
	public DiagnosticCenters updateCenter(DiagnosticCenters center);

	public long countCenters();

}
