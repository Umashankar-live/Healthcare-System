package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bean.DiagnosticCenters;
import com.cg.dao.DiagnosticCenterDao;

@Service
public class DiagnosticCenterServiceImpl implements DiagnosticCenterService {

	@Autowired
	private DiagnosticCenterDao dao;

	@Override
	public DiagnosticCenters addCenter(DiagnosticCenters center) {
		return this.dao.save(center);
	}

	@Override
	public void deleteCenter(Integer centerId) {
		this.dao.deleteById(centerId);
	}

	@Override
	public DiagnosticCenters searchCenter(Integer centerId) {
		return this.dao.findAll().stream().filter(x -> centerId.equals(x.getCenterId())).findAny().orElse(null);
	}

	@Override
	public List<DiagnosticCenters> getAllCenter() {
		return this.dao.findAll();
	}

	@Override
	public DiagnosticCenters updateCenter(DiagnosticCenters center) {
		return this.dao.save(center);
	}

}
